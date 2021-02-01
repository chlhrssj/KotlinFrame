package com.chlhrssj.wanandroid.ui.more.rx

/**
 * Create by rssj on 2021/1/29
 * rx事件传递实现
 */

//真正的被观察者
interface MyObservableOnSubscribe<T> {
    fun setObserver(observer: MyObserver<T>)//设置下游
}

//观察者
interface MyObserver<T> {
    fun onSubscribe()
    fun onNext(item: T)
    fun onError(e: Throwable)
    fun onComplete()
}

open class MyObservable<T>
protected constructor(protected open val source: MyObservableOnSubscribe<T>) {

    //这里接收一个下游对象，
    open fun setObserver(downStream: MyObserver<T>) {
        downStream.onSubscribe()
        source.setObserver(downStream)
    }

    fun <R> map(func: (T) -> R): MyObservable<R> {
        //source就是上游真正的被观察者。
        val map = MyMapObservable(this.source, func)
        return MyObservable(map)
    }

    //静态方法创建一个真正的被观察者
    companion object {
        fun <T> create(emitter: MyObservableOnSubscribe<T>): MyObservable<T> {
            return MyObservable(emitter)
        }
    }

}

class MyMapObservable<T, R>(
    private val source: MyObservableOnSubscribe<T>,
    private val func: ((T) -> R)
) : MyObservableOnSubscribe<R> {

    override fun setObserver(downStream: MyObserver<R>) {
        //此时的downStream就是真正的下游
        val map = MyMapObserver(downStream, func)//创建自己的观察者对象
        source.setObserver(map)//将自己传递给上游
    }
}

class MyMapObserver<T, R>(
    private val downStream: MyObserver<R>,
    private val func: ((T) -> R)
) : MyObserver<T> {

    override fun onSubscribe() {
        downStream.onSubscribe()//当接收到上游传来的订阅后，将事件传递给下游
    }

    override fun onNext(item: T) {
        //应用转换规则，得到转换后的数据
        val result = func.invoke(item)
        //将转换后的数据传递给下游
        downStream.onNext(result)
    }

    override fun onError(e: Throwable) {
        //将错误传递给下游
        downStream.onError(e)
    }

    override fun onComplete() {
        //完成事件传递给下游
        downStream.onComplete()
    }

}