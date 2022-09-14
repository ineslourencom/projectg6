package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import java.util.Objects;

public class BusinessErrorMessage {
    public static final int UNPROCESSABLE_ENTITY = 1;
    public static final int NOT_FOUND = 2;
    public static final int INVALID_ENTRY = 3;
    private String msg;
    private int code;

    public BusinessErrorMessage(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getErrorMessage() {
        return msg;
    }

    public int getErrorCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessErrorMessage message = (BusinessErrorMessage) o;
        return code == message.code && msg.equals(message.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msg, code);
    }
}
