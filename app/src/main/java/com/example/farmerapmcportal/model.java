package com.example.farmerapmcportal;

import android.graphics.Bitmap;

public class model {
    String proname, pprice, pavail;

    public model(String proname, String pprice, String pavail) {
        this.proname = proname;
        this.pprice = pprice;
        this.pavail = pavail;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPavail() {
        return pavail;
    }

    public void setPavail(String pavail) {
        this.pavail = pavail;
    }

}
