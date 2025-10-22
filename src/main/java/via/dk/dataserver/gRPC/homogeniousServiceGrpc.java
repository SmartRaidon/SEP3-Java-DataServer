package via.dk.dataserver.gRPC;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.66.0)",
    comments = "Source: sep3.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class homogeniousServiceGrpc {

  private homogeniousServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "via.dk.dataserver.gRPC.homogeniousService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<via.dk.dataserver.gRPC.Sep3.Request,
      via.dk.dataserver.gRPC.Sep3.Response> getHandleRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "handleRequest",
      requestType = via.dk.dataserver.gRPC.Sep3.Request.class,
      responseType = via.dk.dataserver.gRPC.Sep3.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.dk.dataserver.gRPC.Sep3.Request,
      via.dk.dataserver.gRPC.Sep3.Response> getHandleRequestMethod() {
    io.grpc.MethodDescriptor<via.dk.dataserver.gRPC.Sep3.Request, via.dk.dataserver.gRPC.Sep3.Response> getHandleRequestMethod;
    if ((getHandleRequestMethod = homogeniousServiceGrpc.getHandleRequestMethod) == null) {
      synchronized (homogeniousServiceGrpc.class) {
        if ((getHandleRequestMethod = homogeniousServiceGrpc.getHandleRequestMethod) == null) {
          homogeniousServiceGrpc.getHandleRequestMethod = getHandleRequestMethod =
              io.grpc.MethodDescriptor.<via.dk.dataserver.gRPC.Sep3.Request, via.dk.dataserver.gRPC.Sep3.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "handleRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.dk.dataserver.gRPC.Sep3.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.dk.dataserver.gRPC.Sep3.Response.getDefaultInstance()))
              .setSchemaDescriptor(new homogeniousServiceMethodDescriptorSupplier("handleRequest"))
              .build();
        }
      }
    }
    return getHandleRequestMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static homogeniousServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<homogeniousServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<homogeniousServiceStub>() {
        @java.lang.Override
        public homogeniousServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new homogeniousServiceStub(channel, callOptions);
        }
      };
    return homogeniousServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static homogeniousServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<homogeniousServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<homogeniousServiceBlockingStub>() {
        @java.lang.Override
        public homogeniousServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new homogeniousServiceBlockingStub(channel, callOptions);
        }
      };
    return homogeniousServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static homogeniousServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<homogeniousServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<homogeniousServiceFutureStub>() {
        @java.lang.Override
        public homogeniousServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new homogeniousServiceFutureStub(channel, callOptions);
        }
      };
    return homogeniousServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void handleRequest(via.dk.dataserver.gRPC.Sep3.Request request,
        io.grpc.stub.StreamObserver<via.dk.dataserver.gRPC.Sep3.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHandleRequestMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service homogeniousService.
   */
  public static abstract class homogeniousServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return homogeniousServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service homogeniousService.
   */
  public static final class homogeniousServiceStub
      extends io.grpc.stub.AbstractAsyncStub<homogeniousServiceStub> {
    private homogeniousServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected homogeniousServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new homogeniousServiceStub(channel, callOptions);
    }

    /**
     */
    public void handleRequest(via.dk.dataserver.gRPC.Sep3.Request request,
        io.grpc.stub.StreamObserver<via.dk.dataserver.gRPC.Sep3.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHandleRequestMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service homogeniousService.
   */
  public static final class homogeniousServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<homogeniousServiceBlockingStub> {
    private homogeniousServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected homogeniousServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new homogeniousServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public via.dk.dataserver.gRPC.Sep3.Response handleRequest(via.dk.dataserver.gRPC.Sep3.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHandleRequestMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service homogeniousService.
   */
  public static final class homogeniousServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<homogeniousServiceFutureStub> {
    private homogeniousServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected homogeniousServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new homogeniousServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.dk.dataserver.gRPC.Sep3.Response> handleRequest(
        via.dk.dataserver.gRPC.Sep3.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHandleRequestMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HANDLE_REQUEST = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HANDLE_REQUEST:
          serviceImpl.handleRequest((via.dk.dataserver.gRPC.Sep3.Request) request,
              (io.grpc.stub.StreamObserver<via.dk.dataserver.gRPC.Sep3.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getHandleRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              via.dk.dataserver.gRPC.Sep3.Request,
              via.dk.dataserver.gRPC.Sep3.Response>(
                service, METHODID_HANDLE_REQUEST)))
        .build();
  }

  private static abstract class homogeniousServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    homogeniousServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return via.dk.dataserver.gRPC.Sep3.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("homogeniousService");
    }
  }

  private static final class homogeniousServiceFileDescriptorSupplier
      extends homogeniousServiceBaseDescriptorSupplier {
    homogeniousServiceFileDescriptorSupplier() {}
  }

  private static final class homogeniousServiceMethodDescriptorSupplier
      extends homogeniousServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    homogeniousServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (homogeniousServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new homogeniousServiceFileDescriptorSupplier())
              .addMethod(getHandleRequestMethod())
              .build();
        }
      }
    }
    return result;
  }
}
