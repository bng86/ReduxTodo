package tw.andyang.todo

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

abstract class BaseSpek(root: Root.() -> Unit) : Spek(root){
    companion object {
        fun startHookSchedules() {
            val immediateScheduler = object : Scheduler() {
                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                    // Hack to prevent stack overflows in unit tests when scheduling with a delay;
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Scheduler.Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }
            RxJavaPlugins.setInitIoSchedulerHandler { immediateScheduler }
            RxJavaPlugins.setInitComputationSchedulerHandler { immediateScheduler }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { immediateScheduler }
            RxJavaPlugins.setInitSingleSchedulerHandler { immediateScheduler }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediateScheduler }
        }

        fun stopHookSchedules() {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }
    }
}