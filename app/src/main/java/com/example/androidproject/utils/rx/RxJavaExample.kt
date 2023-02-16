package com.example.androidproject.utils.rx

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class RxJavaExample {

    fun observableJust1() {

        //1 Cпособ
        val developers: io.reactivex.Observable<String> = io.reactivex.Observable.just(
            "IOS",
            "Android",
            "Flutter"
        )

        developers.doAfterNext {
            Log.w("next", it)
        }.doOnError {
            //here will be exception if throw
        }.doOnComplete {
            Log.w("completed", "finish")
        }.subscribe()

    }

    fun observableJust2() {

        val developersAnotherWay: io.reactivex.Observable<String> = io.reactivex.Observable.just(
            "IOS",
            "Android",
            "Flutter"
        )

        developersAnotherWay.subscribe({
            Log.w("next", it)
        }, {
            //here will be exception if throw
        }, {
            Log.w("completed", "finish")
        })
    }


    fun devList() {
        val devList = listOf<String>("IOS", "Android", "Flutter")

        io.reactivex.Observable.create<String> { emitter ->
            devList.forEach { developer ->
                if (developer.isEmpty()) {
                    emitter.onError(Exception("value is empty"))
                }
                emitter.onNext(developer)
            }
        }.doAfterNext {
            Log.w("next", it)
        }.doOnError {
        }.doOnComplete {
            Log.w("complete", "finished")
        }.subscribe({}, { Log.w("error", it.message.toString()) })
    }

    fun reactiv2() {
        io.reactivex.Observable.just("IOS", "Android", "Flutter")
            .subscribeOn(Schedulers.io())
            .flatMap {
                io.reactivex.Observable.just("$it 2")
                    .subscribeOn(Schedulers.io())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.w("result", it.toString())
            }
    }

    fun zipObservable() {
        io.reactivex.Observable.zip(
            io.reactivex.Observable.just("IOS", "Android", "Flutter"),
            io.reactivex.Observable.just("Switch", "Kotlin", "Dart")
        ) { dev, lang ->
            Log.w("result zip", "$dev writes in $lang")
        }.subscribe()
    }

    fun concat() {
        val devs = io.reactivex.Observable.just("IOS", "Android", "Flutter")
        val langs = io.reactivex.Observable.just("Switch", "Kotlin", "Dart")
        val comp = io.reactivex.Observable.just("Apple", "Google")

        io.reactivex.Observable.concat(devs, langs, comp)
            .subscribe { Log.w("result concat", it.toString()) }
    }

    fun publishSubject() {
        val publishSubject = PublishSubject.create<Int>()
        publishSubject.onNext(1)
        publishSubject.onNext(2)
        publishSubject.onNext(3)
        publishSubject.subscribe({ Log.w("publish value", it.toString()) })
        publishSubject.onNext(4)
        publishSubject.onNext(5)
        publishSubject.subscribe({ Log.w("publish value2", it.toString()) })
    }

    fun replaySubject() {
        val replaysSubject = ReplaySubject.create<Int>()
        replaysSubject.onNext(1)
        replaysSubject.onNext(2)
        replaysSubject.onNext(3)
        replaysSubject.subscribe({ Log.w("Early", it.toString()) })
        replaysSubject.onNext(4)
        replaysSubject.onNext(5)
        replaysSubject.subscribe({ Log.w("Later", it.toString()) })
    }

    fun behaviorSubject() {
        val behaviorSubject = BehaviorSubject.create<Int>()
        behaviorSubject.onNext(1)
        behaviorSubject.onNext(2)
        behaviorSubject.onNext(3)
        behaviorSubject.subscribe({ Log.w("publish value", it.toString()) })
        behaviorSubject.onNext(4)
        behaviorSubject.subscribe({ Log.w("publish value2", it.toString()) })
        behaviorSubject.onNext(5)
    }

    fun asyncSubject() {
        val asyncSubject = AsyncSubject.create<Int>()
        asyncSubject.onNext(1)
        asyncSubject.onNext(2)
        asyncSubject.onNext(3)
        asyncSubject.subscribe({ Log.w("publish value", it.toString()) })
        asyncSubject.onNext(4)
        asyncSubject.subscribe({ Log.w("publish value2", it.toString()) })
        asyncSubject.onNext(5)
        asyncSubject.onComplete()
    }
}