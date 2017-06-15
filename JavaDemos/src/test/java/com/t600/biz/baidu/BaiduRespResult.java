package com.t600.biz.baidu;

import java.util.List;

/**
 * Created by Jzhung on 2017/6/15.
 */
public class BaiduRespResult {


    /**
     * result : {"ad_display_type":0,"aladdin_res_num":1,"aladin_query_type":0,"area_profile":0,"business_bound":"","catalogID":0,"cmd_no":2,"count":10,"current_null":0,"data_security_filt_res":0,"db":0,"debug":0,"jump_back":1,"loc_attr":1,"op_gel":0,"page_num":0,"pattern_sign":0,"profile_uid":"2bfcb14cf5cb2cfcc23644f2","qid":"8457498150318908413","requery":"","res_bound":"(12942599,4815939;13014725,4904455)","res_bound_acc":"(0,0;0,0)","res_l":0,"res_x":"0.000000","res_y":"0.000000","result_show":0,"return_query":"北京艺考培训班","rp_strategy":0,"spec_dispnum":0,"spothot":false,"sug_index":-1,"time":0,"total":2405,"total_busline_num":0,"tp":0,"type":11,"wd":"北京艺考培训班","wd2":"","what":"艺考培训班","where":"北京","uii_type":"china_main","region":"0","uii_qt":"poi","login_debug":0,"lbs_forward":{"param":[{"ad_page_logs":"","d_brand_id":"0","d_query_attr_type":"0","d_tag_info_list":"","query_show_click_flag":0,"sample_experiment":[],"view_city":131,"biz_brand_id":"0","biz_query_attr_type":"2","biz_query_show_click_flag":0,"biz_tag_info_list":""},{"ad_page_logs":"","biz_brand_id":"0","biz_query_attr_type":"2","biz_query_show_click_flag":0,"biz_tag_info_list":""}]}}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * ad_display_type : 0
         * aladdin_res_num : 1
         * aladin_query_type : 0
         * area_profile : 0
         * business_bound :
         * catalogID : 0
         * cmd_no : 2
         * count : 10
         * current_null : 0
         * data_security_filt_res : 0
         * db : 0
         * debug : 0
         * jump_back : 1
         * loc_attr : 1
         * op_gel : 0
         * page_num : 0
         * pattern_sign : 0
         * profile_uid : 2bfcb14cf5cb2cfcc23644f2
         * qid : 8457498150318908413
         * requery :
         * res_bound : (12942599,4815939;13014725,4904455)
         * res_bound_acc : (0,0;0,0)
         * res_l : 0
         * res_x : 0.000000
         * res_y : 0.000000
         * result_show : 0
         * return_query : 北京艺考培训班
         * rp_strategy : 0
         * spec_dispnum : 0
         * spothot : false
         * sug_index : -1
         * time : 0
         * total : 2405
         * total_busline_num : 0
         * tp : 0
         * type : 11
         * wd : 北京艺考培训班
         * wd2 :
         * what : 艺考培训班
         * where : 北京
         * uii_type : china_main
         * region : 0
         * uii_qt : poi
         * login_debug : 0
         * lbs_forward : {"param":[{"ad_page_logs":"","d_brand_id":"0","d_query_attr_type":"0","d_tag_info_list":"","query_show_click_flag":0,"sample_experiment":[],"view_city":131},{"ad_page_logs":"","biz_brand_id":"0","biz_query_attr_type":"2","biz_query_show_click_flag":0,"biz_tag_info_list":""}]}
         */

        private int ad_display_type;
        private int aladdin_res_num;
        private int aladin_query_type;
        private int area_profile;
        private String business_bound;
        private int catalogID;
        private int cmd_no;
        private int count;
        private int current_null;
        private int data_security_filt_res;
        private int db;
        private int debug;
        private int jump_back;
        private int loc_attr;
        private int op_gel;
        private int page_num;
        private int pattern_sign;
        private String profile_uid;
        private String qid;
        private String requery;
        private String res_bound;
        private String res_bound_acc;
        private int res_l;
        private String res_x;
        private String res_y;
        private int result_show;
        private String return_query;
        private int rp_strategy;
        private int spec_dispnum;
        private boolean spothot;
        private int sug_index;
        private int time;
        private int total;
        private int total_busline_num;
        private int tp;
        private int type;
        private String wd;
        private String wd2;
        private String what;
        private String where;
        private String uii_type;
        private String region;
        private String uii_qt;
        private int login_debug;
        private LbsForwardBean lbs_forward;

        public int getAd_display_type() {
            return ad_display_type;
        }

        public void setAd_display_type(int ad_display_type) {
            this.ad_display_type = ad_display_type;
        }

        public int getAladdin_res_num() {
            return aladdin_res_num;
        }

        public void setAladdin_res_num(int aladdin_res_num) {
            this.aladdin_res_num = aladdin_res_num;
        }

        public int getAladin_query_type() {
            return aladin_query_type;
        }

        public void setAladin_query_type(int aladin_query_type) {
            this.aladin_query_type = aladin_query_type;
        }

        public int getArea_profile() {
            return area_profile;
        }

        public void setArea_profile(int area_profile) {
            this.area_profile = area_profile;
        }

        public String getBusiness_bound() {
            return business_bound;
        }

        public void setBusiness_bound(String business_bound) {
            this.business_bound = business_bound;
        }

        public int getCatalogID() {
            return catalogID;
        }

        public void setCatalogID(int catalogID) {
            this.catalogID = catalogID;
        }

        public int getCmd_no() {
            return cmd_no;
        }

        public void setCmd_no(int cmd_no) {
            this.cmd_no = cmd_no;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCurrent_null() {
            return current_null;
        }

        public void setCurrent_null(int current_null) {
            this.current_null = current_null;
        }

        public int getData_security_filt_res() {
            return data_security_filt_res;
        }

        public void setData_security_filt_res(int data_security_filt_res) {
            this.data_security_filt_res = data_security_filt_res;
        }

        public int getDb() {
            return db;
        }

        public void setDb(int db) {
            this.db = db;
        }

        public int getDebug() {
            return debug;
        }

        public void setDebug(int debug) {
            this.debug = debug;
        }

        public int getJump_back() {
            return jump_back;
        }

        public void setJump_back(int jump_back) {
            this.jump_back = jump_back;
        }

        public int getLoc_attr() {
            return loc_attr;
        }

        public void setLoc_attr(int loc_attr) {
            this.loc_attr = loc_attr;
        }

        public int getOp_gel() {
            return op_gel;
        }

        public void setOp_gel(int op_gel) {
            this.op_gel = op_gel;
        }

        public int getPage_num() {
            return page_num;
        }

        public void setPage_num(int page_num) {
            this.page_num = page_num;
        }

        public int getPattern_sign() {
            return pattern_sign;
        }

        public void setPattern_sign(int pattern_sign) {
            this.pattern_sign = pattern_sign;
        }

        public String getProfile_uid() {
            return profile_uid;
        }

        public void setProfile_uid(String profile_uid) {
            this.profile_uid = profile_uid;
        }

        public String getQid() {
            return qid;
        }

        public void setQid(String qid) {
            this.qid = qid;
        }

        public String getRequery() {
            return requery;
        }

        public void setRequery(String requery) {
            this.requery = requery;
        }

        public String getRes_bound() {
            return res_bound;
        }

        public void setRes_bound(String res_bound) {
            this.res_bound = res_bound;
        }

        public String getRes_bound_acc() {
            return res_bound_acc;
        }

        public void setRes_bound_acc(String res_bound_acc) {
            this.res_bound_acc = res_bound_acc;
        }

        public int getRes_l() {
            return res_l;
        }

        public void setRes_l(int res_l) {
            this.res_l = res_l;
        }

        public String getRes_x() {
            return res_x;
        }

        public void setRes_x(String res_x) {
            this.res_x = res_x;
        }

        public String getRes_y() {
            return res_y;
        }

        public void setRes_y(String res_y) {
            this.res_y = res_y;
        }

        public int getResult_show() {
            return result_show;
        }

        public void setResult_show(int result_show) {
            this.result_show = result_show;
        }

        public String getReturn_query() {
            return return_query;
        }

        public void setReturn_query(String return_query) {
            this.return_query = return_query;
        }

        public int getRp_strategy() {
            return rp_strategy;
        }

        public void setRp_strategy(int rp_strategy) {
            this.rp_strategy = rp_strategy;
        }

        public int getSpec_dispnum() {
            return spec_dispnum;
        }

        public void setSpec_dispnum(int spec_dispnum) {
            this.spec_dispnum = spec_dispnum;
        }

        public boolean isSpothot() {
            return spothot;
        }

        public void setSpothot(boolean spothot) {
            this.spothot = spothot;
        }

        public int getSug_index() {
            return sug_index;
        }

        public void setSug_index(int sug_index) {
            this.sug_index = sug_index;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal_busline_num() {
            return total_busline_num;
        }

        public void setTotal_busline_num(int total_busline_num) {
            this.total_busline_num = total_busline_num;
        }

        public int getTp() {
            return tp;
        }

        public void setTp(int tp) {
            this.tp = tp;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getWd() {
            return wd;
        }

        public void setWd(String wd) {
            this.wd = wd;
        }

        public String getWd2() {
            return wd2;
        }

        public void setWd2(String wd2) {
            this.wd2 = wd2;
        }

        public String getWhat() {
            return what;
        }

        public void setWhat(String what) {
            this.what = what;
        }

        public String getWhere() {
            return where;
        }

        public void setWhere(String where) {
            this.where = where;
        }

        public String getUii_type() {
            return uii_type;
        }

        public void setUii_type(String uii_type) {
            this.uii_type = uii_type;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getUii_qt() {
            return uii_qt;
        }

        public void setUii_qt(String uii_qt) {
            this.uii_qt = uii_qt;
        }

        public int getLogin_debug() {
            return login_debug;
        }

        public void setLogin_debug(int login_debug) {
            this.login_debug = login_debug;
        }

        public LbsForwardBean getLbs_forward() {
            return lbs_forward;
        }

        public void setLbs_forward(LbsForwardBean lbs_forward) {
            this.lbs_forward = lbs_forward;
        }

        public static class LbsForwardBean {
            private List<ParamBean> param;

            public List<ParamBean> getParam() {
                return param;
            }

            public void setParam(List<ParamBean> param) {
                this.param = param;
            }

            public static class ParamBean {
                /**
                 * ad_page_logs :
                 * d_brand_id : 0
                 * d_query_attr_type : 0
                 * d_tag_info_list :
                 * query_show_click_flag : 0
                 * sample_experiment : []
                 * view_city : 131
                 * biz_brand_id : 0
                 * biz_query_attr_type : 2
                 * biz_query_show_click_flag : 0
                 * biz_tag_info_list :
                 */

                private String ad_page_logs;
                private String d_brand_id;
                private String d_query_attr_type;
                private String d_tag_info_list;
                private int query_show_click_flag;
                private int view_city;
                private String biz_brand_id;
                private String biz_query_attr_type;
                private int biz_query_show_click_flag;
                private String biz_tag_info_list;
                private List<?> sample_experiment;

                public String getAd_page_logs() {
                    return ad_page_logs;
                }

                public void setAd_page_logs(String ad_page_logs) {
                    this.ad_page_logs = ad_page_logs;
                }

                public String getD_brand_id() {
                    return d_brand_id;
                }

                public void setD_brand_id(String d_brand_id) {
                    this.d_brand_id = d_brand_id;
                }

                public String getD_query_attr_type() {
                    return d_query_attr_type;
                }

                public void setD_query_attr_type(String d_query_attr_type) {
                    this.d_query_attr_type = d_query_attr_type;
                }

                public String getD_tag_info_list() {
                    return d_tag_info_list;
                }

                public void setD_tag_info_list(String d_tag_info_list) {
                    this.d_tag_info_list = d_tag_info_list;
                }

                public int getQuery_show_click_flag() {
                    return query_show_click_flag;
                }

                public void setQuery_show_click_flag(int query_show_click_flag) {
                    this.query_show_click_flag = query_show_click_flag;
                }

                public int getView_city() {
                    return view_city;
                }

                public void setView_city(int view_city) {
                    this.view_city = view_city;
                }

                public String getBiz_brand_id() {
                    return biz_brand_id;
                }

                public void setBiz_brand_id(String biz_brand_id) {
                    this.biz_brand_id = biz_brand_id;
                }

                public String getBiz_query_attr_type() {
                    return biz_query_attr_type;
                }

                public void setBiz_query_attr_type(String biz_query_attr_type) {
                    this.biz_query_attr_type = biz_query_attr_type;
                }

                public int getBiz_query_show_click_flag() {
                    return biz_query_show_click_flag;
                }

                public void setBiz_query_show_click_flag(int biz_query_show_click_flag) {
                    this.biz_query_show_click_flag = biz_query_show_click_flag;
                }

                public String getBiz_tag_info_list() {
                    return biz_tag_info_list;
                }

                public void setBiz_tag_info_list(String biz_tag_info_list) {
                    this.biz_tag_info_list = biz_tag_info_list;
                }

                public List<?> getSample_experiment() {
                    return sample_experiment;
                }

                public void setSample_experiment(List<?> sample_experiment) {
                    this.sample_experiment = sample_experiment;
                }
            }
        }
    }
}
