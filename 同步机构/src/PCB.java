public class PCB {
    //  进程名
    private String name;

    //  状态
    private String status;

    //  等待原因
    private String reason;

    //  断点
    private int breakPoint;

    PCB(String name,String status,String reason,int breakPoint){
        this.name = name;
        this.status = status;
        this.reason = reason;
        this.breakPoint = breakPoint;
    }
    PCB(){

    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getBreakPoint() {
        return breakPoint;
    }

    public void setBreakPoint(int breakPoint) {
        this.breakPoint = breakPoint;
    }
}
