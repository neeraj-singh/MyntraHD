package com.neerajsingh.myntrahd.network.response;


public class BidBasket {

    private Long prodId;
    private String uid;
    private String displayImg;
    private String prodTitle;
    private Long mrp;
    private Long userBid;
    private boolean isUnique;
    public Long getProdId() {
        return prodId;
    }
    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getDisplayImg() {
        return displayImg;
    }
    public void setDisplayImg(String displayImg) {
        this.displayImg = displayImg;
    }
    public String getProdTitle() {
        return prodTitle;
    }
    public void setProdTitle(String prodTitle) {
        this.prodTitle = prodTitle;
    }
    public Long getMrp() {
        return mrp;
    }
    public void setMrp(Long mrp) {
        this.mrp = mrp;
    }
    public Long getUserBid() {
        return userBid;
    }
    public void setUserBid(Long userBid) {
        this.userBid = userBid;
    }
    public boolean isUnique() {
        return isUnique;
    }
    public void setUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }
}
