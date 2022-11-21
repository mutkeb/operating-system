public class Order {
    //  操作
    private String action;
    //  页号
    private int pageNum;
    //  单元号
    private String unitNum;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    Order(String action, int pageNum, String unitNum){
        this.action = action;
        this.pageNum = pageNum;
        this.unitNum = unitNum;
    }
}
