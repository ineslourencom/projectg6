package switchtwentyone.project.dto;

import java.util.Objects;

public class NewUserStoryInfoDTO {
    private String statement;
    private String detail;

    public NewUserStoryInfoDTO(String statement, String detail){
        this.statement = statement;
        this.detail = detail;
    }

    public String getStatement(){
        return this.statement;
    }

    public String getDetail(){
        return this.detail;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewUserStoryInfoDTO)) return false;
        NewUserStoryInfoDTO usdto = (NewUserStoryInfoDTO) o;
        return statement.equalsIgnoreCase(usdto.statement)
                && detail.equalsIgnoreCase(usdto.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, detail);
    }

}
