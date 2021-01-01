package bgu.spl.net.api;

public interface Command<T> {
    String execute(T arg);
}
