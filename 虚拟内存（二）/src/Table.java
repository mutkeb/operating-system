public class Table {
    //  页号
    private int pageNUm;
    //  标志
    private int symbol;
    //  主存块号
    private Integer pieceNum;
    //  在磁盘上的位置
    private String position;
    //  修改标志
    private int changeSymbol;

    public int getPageNUm() {
        return pageNUm;
    }

    public void setPageNUm(int pageNUm) {
        this.pageNUm = pageNUm;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public Integer getPieceNum() {
        return pieceNum;
    }

    public void setPieceNum(Integer pieceNum) {
        this.pieceNum = pieceNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    Table(int pageNUm, int symbol, Integer pieceNum, int changeSymbol,String position){
        this.pageNUm = pageNUm;
        this.symbol = symbol;
        this.pieceNum = pieceNum;
        this.changeSymbol = changeSymbol;
        this.position = position;
    }
}
