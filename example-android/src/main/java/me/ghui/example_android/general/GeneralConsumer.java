package me.ghui.example_android.general;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.ghui.example_android.widget.Voast;

/**
 * Created by ghui on 19/10/2017.
 */

public abstract class GeneralConsumer<T> implements Observer<T> {
    @Override
    public void onError(Throwable e) {
        Voast.show(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    public abstract void onConsume(T t);

    @Override
    public void onNext(T t) {
        onConsume(t);
    }
}
