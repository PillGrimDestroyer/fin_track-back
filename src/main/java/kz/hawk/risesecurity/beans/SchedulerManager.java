package kz.hawk.risesecurity.beans;

import kz.greetgo.scheduling.HasScheduled;
import kz.greetgo.scheduling.collector.*;
import kz.greetgo.scheduling.scheduler.Scheduler;
import kz.hawk.risesecurity.util.App;
import kz.hawk.risesecurity.util.HasApplicationFinishing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static kz.greetgo.scheduling.collector.TaskCollector.newTaskCollector;
import static kz.greetgo.scheduling.scheduler.SchedulerBuilder.newSchedulerBuilder;

@Component
@DependsOn("liquibase")
@Slf4j
@RequiredArgsConstructor
public class SchedulerManager implements HasApplicationFinishing, InitializingBean {

  private final List<HasScheduled> schedulers;

  private Scheduler scheduler;

  private static Path getDir() {
    return App.appDir().resolve("scheduler");
  }

  private static String toInfo(Job job) {
    if (!(job instanceof CallMethodJob)) {
      return job.toString();
    }

    try {
      Field controllerField = CallMethodJob.class.getDeclaredField("controller");
      controllerField.setAccessible(true);
      Object controller = controllerField.get(job);

      Field methodField = CallMethodJob.class.getDeclaredField("method");
      methodField.setAccessible(true);
      Method method = (Method) methodField.get(job);

      return controller.getClass().getSimpleName() + "#" + method.getName() + "()";

    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onApplicationFinishing() {
    if (scheduler != null) {
      scheduler.shutdown();
    }
  }

  @Override
  public double stoppingOrderIndex() {
    return 5;
  }

  @Override
  public void afterPropertiesSet() {
    start();
  }

  public void start() {
    SchedulerConfigStore configStore = new SchedulerConfigStoreInFile(getDir());

    List<Task> taskList = newTaskCollector()
      .setSchedulerConfigStore(configStore)
      .addControllers(new ArrayList<>(schedulers))
      .setConfigExtension(".scheduler.txt")
      .setConfigErrorsExtension(".scheduler.errors.txt")
      .getTasks();

    log.info("------------------------------------------- SCHEDULER -------------------------------------------");
    for (Task task : taskList) {
      log.info(toInfo(task.job()) + " <--- " + task.trigger());
    }
    log.info("-------------------------------------------------------------------------------------------------");

    scheduler = newSchedulerBuilder()
      .addTasks(taskList)
      .setThrowCatcher(this::logErrorOnly)
      .setPingDelayMillis(500)
      .build();

    scheduler.startup();
  }

  private void logErrorOnly(Throwable throwable) {
    log.error(throwable.getMessage(), throwable);
  }
}
