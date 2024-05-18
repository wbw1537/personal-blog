package com.wbw1537.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(410, "用户名已存在"),
    PHONENUMBER_EXIST(411, "手机号已存在"),
    EMAIL_EXIST(412, "邮箱已存在"),
    REQUIRE_USERNAME(413, "必需填写用户名"),
    LOGIN_ERROR(414, "用户名或密码错误"),
    CONTENT_NOT_NULL(415, "评论内容不能为空"),
    FILE_TYPE_ERROR(416, "文件类型错误，请上传JPG/JPEG/PNG文件"),
    USERNAME_NOT_NULL(417, "用户名不能为空"),
    NICKNAME_NOT_NULL(418, "昵称不能为空"),
    PASSWORD_NOT_NULL(419, "密码不能为空"),
    EMAIL_NOT_NULL(420, "邮箱不能为空"),
    NICKNAME_EXIST(421, "昵称已存在"),
    TAG_NAME_NULL(422, "标签名不能为空"),
    TAG_REMARK_NULL(423, "标签备注不能为空"),
    PARAM_INVALID(402, "参数为空或无效"),
    UPLOAD_ERROR(503, "上传失败"),
    DOWNLOAD_ERROR(504, "下载失败");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
