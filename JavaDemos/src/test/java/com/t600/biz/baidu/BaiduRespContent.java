package com.t600.biz.baidu;

import java.util.List;

/**
 * Created by Jzhung on 2017/6/15.
 */
public class BaiduRespContent {

    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * acc_flag : 0
         * addr : 朝阳区望京北路20号
         * aoi : 来广营
         * area : 2898
         * area_name : 北京市朝阳区
         * biz_type : 0
         * brand_id : null
         * catalogID : 0
         * cla : []
         * click_flag : 0
         * detail : 1
         * diPointX : 1296633904
         * diPointY : 484063048
         * di_tag : 培训机构 学校 职业培训 教育
         * dis : -1
         * dist2route : 0
         * dist2start : 0
         * ext : {"detail_info":{"app_cross_rcmd":{"is_rcmd":1,"src_type":"wanneng_touchuan","sub_src":"app","transfer":1},"areaid":"2898","description_flag":"","display_info_comment_label":{"hotel":"","life":""},"display_info_redu":"17","from_pds":"1","image":"http://e.hiphotos.baidu.com/map/pic/item/08f790529822720ea07d5a097dcb0a46f31fab96.jpg","image_from":"new_biaozhu","image_num":"1","link":[],"mbc":{"markv":"2"},"name":"北京模特艺考学校","overall_rating":"","phone":"15711336373,(010)86299218","poi_address":"朝阳区望京北路20号","point":{"x":1.296633904E7,"y":4840630.48},"price":"0","price_rating":"0","service_rating":"0","shop_hours":"","shop_hours_flag":"","std_tag":"教育培训;培训机构","tag":"职业培训","technology_rating":"0","validate":1,"weighted_tag":"培训机构:3 学校:3 职业培训:3 教育:3"},"src_name":"education"}
         * ext_display : {"display_info":{"catalog_fields":[],"impression_tag":{"hotel":"","life":""},"redu":"17","source_map":{"catalog":{"field_name":"poi_bank","priority":"0","uid":"10257087862491365637"}},"src_name":"display_info"}}
         * ext_type : 4
         * f_flag : 9
         * father_son : 0
         * flag_type : 513
         * geo : 1|12966339.04,4840630.48;12966339.04,4840630.48|12966339.04,4840630.48;
         * geo_type : 2
         * name : 北京模特艺考学校
         * navi_update_time : 1496639315
         * navi_x : 0
         * navi_y : 0
         * new_catalog_id : 0d0100
         * origin_id : {"lbc_uid":"17742844402325749998"}
         * poiType : 0
         * poi_click_num : 0
         * poi_profile : 0
         * primary_uid : 10257087862491365637
         * prio_flag : 0
         * route_flag : 0
         * show_tag : []
         * status : 1
         * std_tag : 教育培训;培训机构
         * storage_src : api
         * tag : <font color="#c60a00">培训</font>机构 学校 职业<font color="#c60a00">培训</font> 教育
         * tel : 15711336373,(010)86299218
         * ty : 2
         * uid : 446af61fcdbe5acc78f7968b
         * view_type : 0
         * x : 1296633904
         * y : 484063048
         */

        private int acc_flag;
        private String addr;
        private String aoi;
        private int area;
        private String area_name;
        private int biz_type;
        private Object brand_id;
        private int catalogID;
        private int click_flag;
        private int detail;
        private int diPointX;
        private int diPointY;
        private String di_tag;
        private int dis;
        private int dist2route;
        private int dist2start;
        private Object ext;
        private Object ext_display;
        private int ext_type;
        private int f_flag;
        private int father_son;
        private String flag_type;
        private String geo;
        private int geo_type;
        private String name;
        private int navi_update_time;
        private String navi_x;
        private String navi_y;
        private String new_catalog_id;
        private OriginIdBean origin_id;
        private int poiType;
        private int poi_click_num;
        private int poi_profile;
        private String primary_uid;
        private int prio_flag;
        private int route_flag;
        private int status;
        private String std_tag;
        private String storage_src;
        private String tag;
        private String tel;
        private int ty;
        private String uid;
        private int view_type;
        private int x;
        private int y;
        private List<?> cla;
        private List<?> show_tag;

        public int getAcc_flag() {
            return acc_flag;
        }

        public void setAcc_flag(int acc_flag) {
            this.acc_flag = acc_flag;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getAoi() {
            return aoi;
        }

        public void setAoi(String aoi) {
            this.aoi = aoi;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getBiz_type() {
            return biz_type;
        }

        public void setBiz_type(int biz_type) {
            this.biz_type = biz_type;
        }

        public Object getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(Object brand_id) {
            this.brand_id = brand_id;
        }

        public int getCatalogID() {
            return catalogID;
        }

        public void setCatalogID(int catalogID) {
            this.catalogID = catalogID;
        }

        public int getClick_flag() {
            return click_flag;
        }

        public void setClick_flag(int click_flag) {
            this.click_flag = click_flag;
        }

        public int getDetail() {
            return detail;
        }

        public void setDetail(int detail) {
            this.detail = detail;
        }

        public int getDiPointX() {
            return diPointX;
        }

        public void setDiPointX(int diPointX) {
            this.diPointX = diPointX;
        }

        public int getDiPointY() {
            return diPointY;
        }

        public void setDiPointY(int diPointY) {
            this.diPointY = diPointY;
        }

        public String getDi_tag() {
            return di_tag;
        }

        public void setDi_tag(String di_tag) {
            this.di_tag = di_tag;
        }

        public int getDis() {
            return dis;
        }

        public void setDis(int dis) {
            this.dis = dis;
        }

        public int getDist2route() {
            return dist2route;
        }

        public void setDist2route(int dist2route) {
            this.dist2route = dist2route;
        }

        public int getDist2start() {
            return dist2start;
        }

        public void setDist2start(int dist2start) {
            this.dist2start = dist2start;
        }

        public Object getExt() {
            return ext;
        }

        public void setExt(Object ext) {
            this.ext = ext;
        }

        public Object getExt_display() {
            return ext_display;
        }

        public void setExt_display(Object ext_display) {
            this.ext_display = ext_display;
        }

        public int getExt_type() {
            return ext_type;
        }

        public void setExt_type(int ext_type) {
            this.ext_type = ext_type;
        }

        public int getF_flag() {
            return f_flag;
        }

        public void setF_flag(int f_flag) {
            this.f_flag = f_flag;
        }

        public int getFather_son() {
            return father_son;
        }

        public void setFather_son(int father_son) {
            this.father_son = father_son;
        }

        public String getFlag_type() {
            return flag_type;
        }

        public void setFlag_type(String flag_type) {
            this.flag_type = flag_type;
        }

        public String getGeo() {
            return geo;
        }

        public void setGeo(String geo) {
            this.geo = geo;
        }

        public int getGeo_type() {
            return geo_type;
        }

        public void setGeo_type(int geo_type) {
            this.geo_type = geo_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNavi_update_time() {
            return navi_update_time;
        }

        public void setNavi_update_time(int navi_update_time) {
            this.navi_update_time = navi_update_time;
        }

        public String getNavi_x() {
            return navi_x;
        }

        public void setNavi_x(String navi_x) {
            this.navi_x = navi_x;
        }

        public String getNavi_y() {
            return navi_y;
        }

        public void setNavi_y(String navi_y) {
            this.navi_y = navi_y;
        }

        public String getNew_catalog_id() {
            return new_catalog_id;
        }

        public void setNew_catalog_id(String new_catalog_id) {
            this.new_catalog_id = new_catalog_id;
        }

        public OriginIdBean getOrigin_id() {
            return origin_id;
        }

        public void setOrigin_id(OriginIdBean origin_id) {
            this.origin_id = origin_id;
        }

        public int getPoiType() {
            return poiType;
        }

        public void setPoiType(int poiType) {
            this.poiType = poiType;
        }

        public int getPoi_click_num() {
            return poi_click_num;
        }

        public void setPoi_click_num(int poi_click_num) {
            this.poi_click_num = poi_click_num;
        }

        public int getPoi_profile() {
            return poi_profile;
        }

        public void setPoi_profile(int poi_profile) {
            this.poi_profile = poi_profile;
        }

        public String getPrimary_uid() {
            return primary_uid;
        }

        public void setPrimary_uid(String primary_uid) {
            this.primary_uid = primary_uid;
        }

        public int getPrio_flag() {
            return prio_flag;
        }

        public void setPrio_flag(int prio_flag) {
            this.prio_flag = prio_flag;
        }

        public int getRoute_flag() {
            return route_flag;
        }

        public void setRoute_flag(int route_flag) {
            this.route_flag = route_flag;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStd_tag() {
            return std_tag;
        }

        public void setStd_tag(String std_tag) {
            this.std_tag = std_tag;
        }

        public String getStorage_src() {
            return storage_src;
        }

        public void setStorage_src(String storage_src) {
            this.storage_src = storage_src;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getTy() {
            return ty;
        }

        public void setTy(int ty) {
            this.ty = ty;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getView_type() {
            return view_type;
        }

        public void setView_type(int view_type) {
            this.view_type = view_type;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public List<?> getCla() {
            return cla;
        }

        public void setCla(List<?> cla) {
            this.cla = cla;
        }

        public List<?> getShow_tag() {
            return show_tag;
        }

        public void setShow_tag(List<?> show_tag) {
            this.show_tag = show_tag;
        }

        public static class ExtBean {
            /**
             * detail_info : {"app_cross_rcmd":{"is_rcmd":1,"src_type":"wanneng_touchuan","sub_src":"app","transfer":1},"areaid":"2898","description_flag":"","display_info_comment_label":{"hotel":"","life":""},"display_info_redu":"17","from_pds":"1","image":"http://e.hiphotos.baidu.com/map/pic/item/08f790529822720ea07d5a097dcb0a46f31fab96.jpg","image_from":"new_biaozhu","image_num":"1","link":[],"mbc":{"markv":"2"},"name":"北京模特艺考学校","overall_rating":"","phone":"15711336373,(010)86299218","poi_address":"朝阳区望京北路20号","point":{"x":1.296633904E7,"y":4840630.48},"price":"0","price_rating":"0","service_rating":"0","shop_hours":"","shop_hours_flag":"","std_tag":"教育培训;培训机构","tag":"职业培训","technology_rating":"0","validate":1,"weighted_tag":"培训机构:3 学校:3 职业培训:3 教育:3"}
             * src_name : education
             */

            private DetailInfoBean detail_info;
            private String src_name;

            public DetailInfoBean getDetail_info() {
                return detail_info;
            }

            public void setDetail_info(DetailInfoBean detail_info) {
                this.detail_info = detail_info;
            }

            public String getSrc_name() {
                return src_name;
            }

            public void setSrc_name(String src_name) {
                this.src_name = src_name;
            }

            public static class DetailInfoBean {
                /**
                 * app_cross_rcmd : {"is_rcmd":1,"src_type":"wanneng_touchuan","sub_src":"app","transfer":1}
                 * areaid : 2898
                 * description_flag :
                 * display_info_comment_label : {"hotel":"","life":""}
                 * display_info_redu : 17
                 * from_pds : 1
                 * image : http://e.hiphotos.baidu.com/map/pic/item/08f790529822720ea07d5a097dcb0a46f31fab96.jpg
                 * image_from : new_biaozhu
                 * image_num : 1
                 * link : []
                 * mbc : {"markv":"2"}
                 * name : 北京模特艺考学校
                 * overall_rating :
                 * phone : 15711336373,(010)86299218
                 * poi_address : 朝阳区望京北路20号
                 * point : {"x":1.296633904E7,"y":4840630.48}
                 * price : 0
                 * price_rating : 0
                 * service_rating : 0
                 * shop_hours :
                 * shop_hours_flag :
                 * std_tag : 教育培训;培训机构
                 * tag : 职业培训
                 * technology_rating : 0
                 * validate : 1
                 * weighted_tag : 培训机构:3 学校:3 职业培训:3 教育:3
                 */

                private AppCrossRcmdBean app_cross_rcmd;
                private String areaid;
                private String description_flag;
                private DisplayInfoCommentLabelBean display_info_comment_label;
                private String display_info_redu;
                private String from_pds;
                private String image;
                private String image_from;
                private String image_num;
                private MbcBean mbc;
                private String name;
                private String overall_rating;
                private String phone;
                private String poi_address;
                private PointBean point;
                private String price;
                private String price_rating;
                private String service_rating;
                private String shop_hours;
                private String shop_hours_flag;
                private String std_tag;
                private String tag;
                private String technology_rating;
                private int validate;
                private String weighted_tag;
                private List<?> link;

                public AppCrossRcmdBean getApp_cross_rcmd() {
                    return app_cross_rcmd;
                }

                public void setApp_cross_rcmd(AppCrossRcmdBean app_cross_rcmd) {
                    this.app_cross_rcmd = app_cross_rcmd;
                }

                public String getAreaid() {
                    return areaid;
                }

                public void setAreaid(String areaid) {
                    this.areaid = areaid;
                }

                public String getDescription_flag() {
                    return description_flag;
                }

                public void setDescription_flag(String description_flag) {
                    this.description_flag = description_flag;
                }

                public DisplayInfoCommentLabelBean getDisplay_info_comment_label() {
                    return display_info_comment_label;
                }

                public void setDisplay_info_comment_label(DisplayInfoCommentLabelBean display_info_comment_label) {
                    this.display_info_comment_label = display_info_comment_label;
                }

                public String getDisplay_info_redu() {
                    return display_info_redu;
                }

                public void setDisplay_info_redu(String display_info_redu) {
                    this.display_info_redu = display_info_redu;
                }

                public String getFrom_pds() {
                    return from_pds;
                }

                public void setFrom_pds(String from_pds) {
                    this.from_pds = from_pds;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getImage_from() {
                    return image_from;
                }

                public void setImage_from(String image_from) {
                    this.image_from = image_from;
                }

                public String getImage_num() {
                    return image_num;
                }

                public void setImage_num(String image_num) {
                    this.image_num = image_num;
                }

                public MbcBean getMbc() {
                    return mbc;
                }

                public void setMbc(MbcBean mbc) {
                    this.mbc = mbc;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOverall_rating() {
                    return overall_rating;
                }

                public void setOverall_rating(String overall_rating) {
                    this.overall_rating = overall_rating;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getPoi_address() {
                    return poi_address;
                }

                public void setPoi_address(String poi_address) {
                    this.poi_address = poi_address;
                }

                public PointBean getPoint() {
                    return point;
                }

                public void setPoint(PointBean point) {
                    this.point = point;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getPrice_rating() {
                    return price_rating;
                }

                public void setPrice_rating(String price_rating) {
                    this.price_rating = price_rating;
                }

                public String getService_rating() {
                    return service_rating;
                }

                public void setService_rating(String service_rating) {
                    this.service_rating = service_rating;
                }

                public String getShop_hours() {
                    return shop_hours;
                }

                public void setShop_hours(String shop_hours) {
                    this.shop_hours = shop_hours;
                }

                public String getShop_hours_flag() {
                    return shop_hours_flag;
                }

                public void setShop_hours_flag(String shop_hours_flag) {
                    this.shop_hours_flag = shop_hours_flag;
                }

                public String getStd_tag() {
                    return std_tag;
                }

                public void setStd_tag(String std_tag) {
                    this.std_tag = std_tag;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getTechnology_rating() {
                    return technology_rating;
                }

                public void setTechnology_rating(String technology_rating) {
                    this.technology_rating = technology_rating;
                }

                public int getValidate() {
                    return validate;
                }

                public void setValidate(int validate) {
                    this.validate = validate;
                }

                public String getWeighted_tag() {
                    return weighted_tag;
                }

                public void setWeighted_tag(String weighted_tag) {
                    this.weighted_tag = weighted_tag;
                }

                public List<?> getLink() {
                    return link;
                }

                public void setLink(List<?> link) {
                    this.link = link;
                }

                public static class AppCrossRcmdBean {
                    /**
                     * is_rcmd : 1
                     * src_type : wanneng_touchuan
                     * sub_src : app
                     * transfer : 1
                     */

                    private int is_rcmd;
                    private String src_type;
                    private String sub_src;
                    private int transfer;

                    public int getIs_rcmd() {
                        return is_rcmd;
                    }

                    public void setIs_rcmd(int is_rcmd) {
                        this.is_rcmd = is_rcmd;
                    }

                    public String getSrc_type() {
                        return src_type;
                    }

                    public void setSrc_type(String src_type) {
                        this.src_type = src_type;
                    }

                    public String getSub_src() {
                        return sub_src;
                    }

                    public void setSub_src(String sub_src) {
                        this.sub_src = sub_src;
                    }

                    public int getTransfer() {
                        return transfer;
                    }

                    public void setTransfer(int transfer) {
                        this.transfer = transfer;
                    }
                }

                public static class DisplayInfoCommentLabelBean {
                    /**
                     * hotel :
                     * life :
                     */

                    private String hotel;
                    private String life;

                    public String getHotel() {
                        return hotel;
                    }

                    public void setHotel(String hotel) {
                        this.hotel = hotel;
                    }

                    public String getLife() {
                        return life;
                    }

                    public void setLife(String life) {
                        this.life = life;
                    }
                }

                public static class MbcBean {
                    /**
                     * markv : 2
                     */

                    private String markv;

                    public String getMarkv() {
                        return markv;
                    }

                    public void setMarkv(String markv) {
                        this.markv = markv;
                    }
                }

                public static class PointBean {
                    /**
                     * x : 1.296633904E7
                     * y : 4840630.48
                     */

                    private double x;
                    private double y;

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
                }
            }
        }

        public static class ExtDisplayBean {
            /**
             * display_info : {"catalog_fields":[],"impression_tag":{"hotel":"","life":""},"redu":"17","source_map":{"catalog":{"field_name":"poi_bank","priority":"0","uid":"10257087862491365637"}},"src_name":"display_info"}
             */

            private DisplayInfoBean display_info;

            public DisplayInfoBean getDisplay_info() {
                return display_info;
            }

            public void setDisplay_info(DisplayInfoBean display_info) {
                this.display_info = display_info;
            }

            public static class DisplayInfoBean {
                /**
                 * catalog_fields : []
                 * impression_tag : {"hotel":"","life":""}
                 * redu : 17
                 * source_map : {"catalog":{"field_name":"poi_bank","priority":"0","uid":"10257087862491365637"}}
                 * src_name : display_info
                 */

                private ImpressionTagBean impression_tag;
                private String redu;
                private SourceMapBean source_map;
                private String src_name;
                private List<?> catalog_fields;

                public ImpressionTagBean getImpression_tag() {
                    return impression_tag;
                }

                public void setImpression_tag(ImpressionTagBean impression_tag) {
                    this.impression_tag = impression_tag;
                }

                public String getRedu() {
                    return redu;
                }

                public void setRedu(String redu) {
                    this.redu = redu;
                }

                public SourceMapBean getSource_map() {
                    return source_map;
                }

                public void setSource_map(SourceMapBean source_map) {
                    this.source_map = source_map;
                }

                public String getSrc_name() {
                    return src_name;
                }

                public void setSrc_name(String src_name) {
                    this.src_name = src_name;
                }

                public List<?> getCatalog_fields() {
                    return catalog_fields;
                }

                public void setCatalog_fields(List<?> catalog_fields) {
                    this.catalog_fields = catalog_fields;
                }

                public static class ImpressionTagBean {
                    /**
                     * hotel :
                     * life :
                     */

                    private String hotel;
                    private String life;

                    public String getHotel() {
                        return hotel;
                    }

                    public void setHotel(String hotel) {
                        this.hotel = hotel;
                    }

                    public String getLife() {
                        return life;
                    }

                    public void setLife(String life) {
                        this.life = life;
                    }
                }

                public static class SourceMapBean {
                    /**
                     * catalog : {"field_name":"poi_bank","priority":"0","uid":"10257087862491365637"}
                     */

                    private CatalogBean catalog;

                    public CatalogBean getCatalog() {
                        return catalog;
                    }

                    public void setCatalog(CatalogBean catalog) {
                        this.catalog = catalog;
                    }

                    public static class CatalogBean {
                        /**
                         * field_name : poi_bank
                         * priority : 0
                         * uid : 10257087862491365637
                         */

                        private String field_name;
                        private String priority;
                        private String uid;

                        public String getField_name() {
                            return field_name;
                        }

                        public void setField_name(String field_name) {
                            this.field_name = field_name;
                        }

                        public String getPriority() {
                            return priority;
                        }

                        public void setPriority(String priority) {
                            this.priority = priority;
                        }

                        public String getUid() {
                            return uid;
                        }

                        public void setUid(String uid) {
                            this.uid = uid;
                        }
                    }
                }
            }
        }

        public static class OriginIdBean {
            /**
             * lbc_uid : 17742844402325749998
             */

            private String lbc_uid;

            public String getLbc_uid() {
                return lbc_uid;
            }

            public void setLbc_uid(String lbc_uid) {
                this.lbc_uid = lbc_uid;
            }
        }
    }
}
