package id.study.demo.core.callback;

import id.study.demo.common.wrapper.ApiResponse;

public class ProcessCallbackSupport {
    public static <T, R> ApiResponse<R> execute(ProcessorCallback<T, R> process) {
        var request = process.composeRequest();
        var processors = process.getProcessors();
        return ApiResponse.success(processors.process(request));
    }
}
