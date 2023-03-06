package app.dtos.responses;

public class FindVoterResponse {
    private int voterId;
    private String fullName;
    private String age;
    private String gender;
    private String state;

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "FindVoterResponse{" +
                "voterId=" + voterId +
                ", fullName='" + fullName + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
