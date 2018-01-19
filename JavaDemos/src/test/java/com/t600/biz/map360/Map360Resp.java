package com.t600.biz.map360;

import java.util.List;

/**
 * Created by Jzhung on 2017/7/31.
 */
public class Map360Resp {
    public List<Item> poi;

    public List<Item> getPoi() {
        return poi;
    }

    public void setPoi(List<Item> poi) {
        this.poi = poi;
    }

    public class Item{
        /**
         * name : 北京电化教育馆
         * x : 116.37879
         * y : 39.93369
         * pguid : 47080e4f101e7864
         * address : 北京市西城区地安门西大街153号北门
         * b_area_name :
         * children : null
         * primaryid : 47080e4f101e7864
         * extParentid : 47080e4f101e7864
         * bounds :
         * suspend : 0
         * adcode : 110102
         * cityid : 110000
         * tel : 010-66521566
         * type : 文化/媒体;科技馆|科教文化服务;科技馆
         * citycode :
         * province : 北京市
         * city : 北京市
         * area : 西城区
         * tels : null
         * ext : {"depth":1,"parentid":"47080e4f101e7864"}
         * name_wipe : 北京电化教育馆
         * se : {"prefix":"1","docId":"27039264","sort0":"2271","match info":"pv=0,dist=471,tr=249,qr=0,cr=0","vertical_match_info":"KPROXIMITY=47035,KBOOST=449,KTITLERATE=40,KHITQUALITY=1,KRANKLEVEL=2,, is_title_exactly_match:0, is_alias_exactly_match:0, is_name_exactly_match:0, can_weaken_doc_rank:0, hit_quality:1,main_cat_new:7, has tags hit:1"}
         * cat : 37
         * cat_new : 7
         * cat_new_name : 文化场所
         * cat_new_path : 72,7
         * category : culture
         */

        private String name;
        private double x;
        private double y;
        private String pguid;
        private String address;
        private String b_area_name;
        private Object children;
        private String primaryid;
        private String extParentid;
        private String bounds;
        private int suspend;
        private String adcode;
        private String cityid;
        private String tel;
        private String type;
        private String citycode;
        private String province;
        private String city;
        private String area;
        private Object tels;
        private ExtBean ext;
        private String name_wipe;
        private Object se;
        private int cat;
        private String cat_new;
        private String cat_new_name;
        private String cat_new_path;
        private String category;

        public  class ExtBean {
            /**
             * depth : 1
             * parentid : 47080e4f101e7864
             */

            private int depth;
            private String parentid;

            public int getDepth() {
                return depth;
            }

            public void setDepth(int depth) {
                this.depth = depth;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public String getPguid() {
            return pguid;
        }

        public void setPguid(String pguid) {
            this.pguid = pguid;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getB_area_name() {
            return b_area_name;
        }

        public void setB_area_name(String b_area_name) {
            this.b_area_name = b_area_name;
        }

        public Object getChildren() {
            return children;
        }

        public void setChildren(Object children) {
            this.children = children;
        }

        public String getPrimaryid() {
            return primaryid;
        }

        public void setPrimaryid(String primaryid) {
            this.primaryid = primaryid;
        }

        public String getExtParentid() {
            return extParentid;
        }

        public void setExtParentid(String extParentid) {
            this.extParentid = extParentid;
        }

        public String getBounds() {
            return bounds;
        }

        public void setBounds(String bounds) {
            this.bounds = bounds;
        }

        public int getSuspend() {
            return suspend;
        }

        public void setSuspend(int suspend) {
            this.suspend = suspend;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public Object getTels() {
            return tels;
        }

        public void setTels(Object tels) {
            this.tels = tels;
        }

        public ExtBean getExt() {
            return ext;
        }

        public void setExt(ExtBean ext) {
            this.ext = ext;
        }

        public String getName_wipe() {
            return name_wipe;
        }

        public void setName_wipe(String name_wipe) {
            this.name_wipe = name_wipe;
        }

        public Object getSe() {
            return se;
        }

        public void setSe(Object se) {
            this.se = se;
        }

        public int getCat() {
            return cat;
        }

        public void setCat(int cat) {
            this.cat = cat;
        }

        public String getCat_new() {
            return cat_new;
        }

        public void setCat_new(String cat_new) {
            this.cat_new = cat_new;
        }

        public String getCat_new_name() {
            return cat_new_name;
        }

        public void setCat_new_name(String cat_new_name) {
            this.cat_new_name = cat_new_name;
        }

        public String getCat_new_path() {
            return cat_new_path;
        }

        public void setCat_new_path(String cat_new_path) {
            this.cat_new_path = cat_new_path;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }


}
