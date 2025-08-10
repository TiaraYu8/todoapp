package id.study.demo.core.callback;

import id.study.demo.core.processors.ProcessorTemplate;

public interface ProcessorCallback<T, R> {
    T composeRequest();

    ProcessorTemplate<T, R> getProcessors();
}
