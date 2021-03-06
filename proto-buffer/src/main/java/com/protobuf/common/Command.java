// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Command.proto

package com.protobuf.common;

public final class Command {
  private Command() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code com.lyh.protocol.CodeType}
   */
  public enum CodeType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     * 0x0开头的是基础消息，不能改变
     * </pre>
     *
     * <code>CLIENT_INFO = 1;</code>
     */
    CLIENT_INFO(1),
    /**
     * <pre>
     * 服务器信息,服务器数据都由这个协议处理
     * </pre>
     *
     * <code>SERVER_INFO = 2;</code>
     */
    SERVER_INFO(2),
    /**
     * <pre>
     * 心跳
     * </pre>
     *
     * <code>HEART_INFO = 65535;</code>
     */
    HEART_INFO(65535),
    /**
     * <pre>
     * 客户端退出请求
     * </pre>
     *
     * <code>CLIENT_EXIT_REQUEST = 3;</code>
     */
    CLIENT_EXIT_REQUEST(3),
    /**
     * <pre>
     * 客户端退出返回
     * </pre>
     *
     * <code>CLIENT_EXIT_RESPONSE = 4;</code>
     */
    CLIENT_EXIT_RESPONSE(4),
    /**
     * <pre>
     * 客户端连接成功请求
     * </pre>
     *
     * <code>CLIENT_CONNECT_SUCCESS_REQUEST = 5;</code>
     */
    CLIENT_CONNECT_SUCCESS_REQUEST(5),
    /**
     * <pre>
     * 客户端连接成功返回
     * </pre>
     *
     * <code>CLIENT_CONNECT_SUCCESS_RESPONSE = 6;</code>
     */
    CLIENT_CONNECT_SUCCESS_RESPONSE(6),
    /**
     * <pre>
     * 0x2开头的是游戏逻辑服消息
     * </pre>
     *
     * <code>PLAYER_ENTER_GAME_REQUEST = 131073;</code>
     */
    PLAYER_ENTER_GAME_REQUEST(131073),
    /**
     * <pre>
     * 玩家进入游戏服返回
     * </pre>
     *
     * <code>PLAYER_ENTER_GAME_RESPONSE = 131074;</code>
     */
    PLAYER_ENTER_GAME_RESPONSE(131074),
    /**
     * <pre>
     * 玩家进入游戏房间请求
     * </pre>
     *
     * <code>PLAYER_ENTER_GAME_ROOM_REQUEST = 131075;</code>
     */
    PLAYER_ENTER_GAME_ROOM_REQUEST(131075),
    /**
     * <pre>
     * 玩家进入游戏房间返回
     * </pre>
     *
     * <code>PLAYER_ENTER_GAME_ROOM_RESPONSE = 131076;</code>
     */
    PLAYER_ENTER_GAME_ROOM_RESPONSE(131076),
    /**
     * <pre>
     * 玩家离开房间请求
     * </pre>
     *
     * <code>PLAYER_LEAVE_GAME_ROOM_REQUEST = 131077;</code>
     */
    PLAYER_LEAVE_GAME_ROOM_REQUEST(131077),
    /**
     * <pre>
     * 玩家离开房间返回
     * </pre>
     *
     * <code>PLAYER_LEAVE_GAME_ROOM_RESPONSE = 131078;</code>
     */
    PLAYER_LEAVE_GAME_ROOM_RESPONSE(131078),
    ;

    /**
     * <pre>
     * 0x0开头的是基础消息，不能改变
     * </pre>
     *
     * <code>CLIENT_INFO = 1;</code>
     */
    public static final int CLIENT_INFO_VALUE = 1;
    /**
     * <pre>
     * 服务器信息,服务器数据都由这个协议处理
     * </pre>
     *
     * <code>SERVER_INFO = 2;</code>
     */
    public static final int SERVER_INFO_VALUE = 2;
    /**
     * <pre>
     * 心跳
     * </pre>
     *
     * <code>HEART_INFO = 65535;</code>
     */
    public static final int HEART_INFO_VALUE = 65535;
    /**
     * <pre>
     * 客户端退出请求
     * </pre>
     *
     * <code>CLIENT_EXIT_REQUEST = 3;</code>
     */
    public static final int CLIENT_EXIT_REQUEST_VALUE = 3;
    /**
     * <pre>
     * 客户端退出返回
     * </pre>
     *
     * <code>CLIENT_EXIT_RESPONSE = 4;</code>
     */
    public static final int CLIENT_EXIT_RESPONSE_VALUE = 4;
    /**
     * <pre>
     * 客户端连接成功请求
     * </pre>
     *
     * <code>CLIENT_CONNECT_SUCCESS_REQUEST = 5;</code>
     */
    public static final int CLIENT_CONNECT_SUCCESS_REQUEST_VALUE = 5;
    /**
     * <pre>
     * 客户端连接成功返回
     * </pre>
     *
     * <code>CLIENT_CONNECT_SUCCESS_RESPONSE = 6;</code>
     */
    public static final int CLIENT_CONNECT_SUCCESS_RESPONSE_VALUE = 6;
    /**
     * <pre>
     * 0x2开头的是游戏逻辑服消息
     * </pre>
     *
     * <code>PLAYER_ENTER_GAME_REQUEST = 131073;</code>
     */
    public static final int PLAYER_ENTER_GAME_REQUEST_VALUE = 131073;
    /**
     * <pre>
     * 玩家进入游戏服返回
     * </pre>
     *
     * <code>PLAYER_ENTER_GAME_RESPONSE = 131074;</code>
     */
    public static final int PLAYER_ENTER_GAME_RESPONSE_VALUE = 131074;
    /**
     * <pre>
     * 玩家进入游戏房间请求
     * </pre>
     *
     * <code>PLAYER_ENTER_GAME_ROOM_REQUEST = 131075;</code>
     */
    public static final int PLAYER_ENTER_GAME_ROOM_REQUEST_VALUE = 131075;
    /**
     * <pre>
     * 玩家进入游戏房间返回
     * </pre>
     *
     * <code>PLAYER_ENTER_GAME_ROOM_RESPONSE = 131076;</code>
     */
    public static final int PLAYER_ENTER_GAME_ROOM_RESPONSE_VALUE = 131076;
    /**
     * <pre>
     * 玩家离开房间请求
     * </pre>
     *
     * <code>PLAYER_LEAVE_GAME_ROOM_REQUEST = 131077;</code>
     */
    public static final int PLAYER_LEAVE_GAME_ROOM_REQUEST_VALUE = 131077;
    /**
     * <pre>
     * 玩家离开房间返回
     * </pre>
     *
     * <code>PLAYER_LEAVE_GAME_ROOM_RESPONSE = 131078;</code>
     */
    public static final int PLAYER_LEAVE_GAME_ROOM_RESPONSE_VALUE = 131078;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static CodeType valueOf(int value) {
      return forNumber(value);
    }

    public static CodeType forNumber(int value) {
      switch (value) {
        case 1: return CLIENT_INFO;
        case 2: return SERVER_INFO;
        case 65535: return HEART_INFO;
        case 3: return CLIENT_EXIT_REQUEST;
        case 4: return CLIENT_EXIT_RESPONSE;
        case 5: return CLIENT_CONNECT_SUCCESS_REQUEST;
        case 6: return CLIENT_CONNECT_SUCCESS_RESPONSE;
        case 131073: return PLAYER_ENTER_GAME_REQUEST;
        case 131074: return PLAYER_ENTER_GAME_RESPONSE;
        case 131075: return PLAYER_ENTER_GAME_ROOM_REQUEST;
        case 131076: return PLAYER_ENTER_GAME_ROOM_RESPONSE;
        case 131077: return PLAYER_LEAVE_GAME_ROOM_REQUEST;
        case 131078: return PLAYER_LEAVE_GAME_ROOM_RESPONSE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<CodeType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        CodeType> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<CodeType>() {
            public CodeType findValueByNumber(int number) {
              return CodeType.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.protobuf.common.Command.getDescriptor().getEnumTypes().get(0);
    }

    private static final CodeType[] VALUES = values();

    public static CodeType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private CodeType(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.lyh.protocol.CodeType)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rCommand.proto\022\020com.lyh.protocol*\227\003\n\010Co" +
      "deType\022\017\n\013CLIENT_INFO\020\001\022\017\n\013SERVER_INFO\020\002" +
      "\022\020\n\nHEART_INFO\020\377\377\003\022\027\n\023CLIENT_EXIT_REQUES" +
      "T\020\003\022\030\n\024CLIENT_EXIT_RESPONSE\020\004\022\"\n\036CLIENT_" +
      "CONNECT_SUCCESS_REQUEST\020\005\022#\n\037CLIENT_CONN" +
      "ECT_SUCCESS_RESPONSE\020\006\022\037\n\031PLAYER_ENTER_G" +
      "AME_REQUEST\020\201\200\010\022 \n\032PLAYER_ENTER_GAME_RES" +
      "PONSE\020\202\200\010\022$\n\036PLAYER_ENTER_GAME_ROOM_REQU" +
      "EST\020\203\200\010\022%\n\037PLAYER_ENTER_GAME_ROOM_RESPON" +
      "SE\020\204\200\010\022$\n\036PLAYER_LEAVE_GAME_ROOM_REQUEST" +
      "\020\205\200\010\022%\n\037PLAYER_LEAVE_GAME_ROOM_RESPONSE\020" +
      "\206\200\010B\025\n\023com.protobuf.common"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
