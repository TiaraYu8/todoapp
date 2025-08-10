package id.study.demo.core.processors;

public interface ProcessorTemplate<T, R> {
    R process(T request);
}
