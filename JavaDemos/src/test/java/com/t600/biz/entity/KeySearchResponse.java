package com.t600.biz.entity;

import java.util.List;

/** 高德查询地址API响应
 * Created by Jzhung on 2017/5/18.
 */
public class KeySearchResponse {

    /**
     * status : 1
     * searchOpt : {"city":"440100","pageSize":"20","pageIndex":"1","ac":"false","keyword":"海珠区教育局","type":"4"}
     * data : {"codepoint":0,"code":"1","suggestion":{},"timestamp":"1495091072.94","lqii":{"cache_directive":{"cache_filter":{"flag":"1","expires":"24"},"cache_all":{"flag":"0"}},"query_intent":{"cate_list":"","type":"1000","cate":"","cate_ext":""},"general_flag":"","specialclassify":"0","view_region":"113.257124700,23.108984000,113.345448300,23.081828000","suggestcontent":"","pdheatmap":"","render_name_flag":"1","is_current_city":"2","expand_range_tip":"","suggest_query":{"data":[],"col":"","row":""},"change_query_tip":"","is_tupu_sug":"0","has_recommend":"0","brand_intent":{"t_tag":"","flag":"0","cate":"","name":""},"suggestionview":"1","change_query_type":"","business":"","car_icon_flag":"0","slayer_type":"none","show_pic":"","is_view_city":"1","querytype":"5","self_navigation":"","showaoi":"","utd_sceneid":"1000","distance_info":"","slayer":"0","target_view_city":"","call_taxi":"0","preload_next_page":"0","magic_box":{},"activity":{"new_year":{"flag":"0","tip":""}}},"is_general_search":"0","result":"true","keywords":"海珠区教育局","message":"Successful.","total":"12","busline_list":[],"bus_list":[],"general_flag":"0","bounds":"113.264485;23.084091;113.338088;23.106694","version":"2.0-3.0.7137.2015","busline_count":"0","magicbox_data":{},"interior_count":"0","poi_list":[{"rating":"2","tel":"020-84417850","typecode":"130104","areacode":"020","cityname":"广州市","display_brand":"","shape_region":"","longitude":"113.264485","address":"同福东路486号","cinemazuo_flag":"0","diner_flag":"0","id":"B00140DU7A","name":"广州市海珠区教育局","group_flag":"0","distance":"0","entrances":[],"recommend_flag":"3","exits":[],"adcode":"440105","domain_list":[{"type":"html","id":"1015","name":"poiclosed"},{"type":"img","id":"1003","name":"icon"},{"type":"html","id":"1011","name":"traffic"},{"type":"text","id":"1014","name":"roadaoi"},{"type":"button","id":"1012","name":"ext_btn"},{"type":"webimg","id":"1009","value":"http://store.is.autonavi.com/showpic/8e17c1d1f5139aa05d34fb5dcfaee86a","name":"pic_info"},{"type":"img","id":"1008","name":"overbooked"},{"type":"img","id":"1007","name":"moreservice"},{"type":"html","id":"1006","name":"tag"},{"type":"text","id":"1005","value":"查看出入口、停车场","name":"parent_info"},{"pys":"23.106721","poiids":"B0FFH693QT","name":"parent_other_rel","pxs":"113.264572","childtype":"41","poiname":"广州市海珠区教育局停车场","address":"同福东路486号广州市海珠区教育局","shortname":"停车场","type":"array","id":"1004"},{"type":"text","id":"1010","name":"business_area"},{"type":"html","id":"1002","name":"deepinfo"},{"type":"html","id":"1001","name":"price"},{"type":"text","id":"1013","name":"aoi"}],"newtype":"130104","disp_name":"广州市海珠区教育局","cpdata":"","latitude":"23.106612","discount_flag":"0"}]}
     */

    private String status;
    private SearchOptBean searchOpt;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SearchOptBean getSearchOpt() {
        return searchOpt;
    }

    public void setSearchOpt(SearchOptBean searchOpt) {
        this.searchOpt = searchOpt;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class SearchOptBean {
        /**
         * city : 440100
         * pageSize : 20
         * pageIndex : 1
         * ac : false
         * keyword : 海珠区教育局
         * type : 4
         */

        private String city;
        private String pageSize;
        private String pageIndex;
        private String ac;
        private String keyword;
        private String type;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(String pageIndex) {
            this.pageIndex = pageIndex;
        }

        public String getAc() {
            return ac;
        }

        public void setAc(String ac) {
            this.ac = ac;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class DataBean {
        /**
         * codepoint : 0
         * code : 1
         * suggestion : {}
         * timestamp : 1495091072.94
         * lqii : {"cache_directive":{"cache_filter":{"flag":"1","expires":"24"},"cache_all":{"flag":"0"}},"query_intent":{"cate_list":"","type":"1000","cate":"","cate_ext":""},"general_flag":"","specialclassify":"0","view_region":"113.257124700,23.108984000,113.345448300,23.081828000","suggestcontent":"","pdheatmap":"","render_name_flag":"1","is_current_city":"2","expand_range_tip":"","suggest_query":{"data":[],"col":"","row":""},"change_query_tip":"","is_tupu_sug":"0","has_recommend":"0","brand_intent":{"t_tag":"","flag":"0","cate":"","name":""},"suggestionview":"1","change_query_type":"","business":"","car_icon_flag":"0","slayer_type":"none","show_pic":"","is_view_city":"1","querytype":"5","self_navigation":"","showaoi":"","utd_sceneid":"1000","distance_info":"","slayer":"0","target_view_city":"","call_taxi":"0","preload_next_page":"0","magic_box":{},"activity":{"new_year":{"flag":"0","tip":""}}}
         * is_general_search : 0
         * result : true
         * keywords : 海珠区教育局
         * message : Successful.
         * total : 12
         * busline_list : []
         * bus_list : []
         * general_flag : 0
         * bounds : 113.264485;23.084091;113.338088;23.106694
         * version : 2.0-3.0.7137.2015
         * busline_count : 0
         * magicbox_data : {}
         * interior_count : 0
         * poi_list : [{"rating":"2","tel":"020-84417850","typecode":"130104","areacode":"020","cityname":"广州市","display_brand":"","shape_region":"","longitude":"113.264485","address":"同福东路486号","cinemazuo_flag":"0","diner_flag":"0","id":"B00140DU7A","name":"广州市海珠区教育局","group_flag":"0","distance":"0","entrances":[],"recommend_flag":"3","exits":[],"adcode":"440105","domain_list":[{"type":"html","id":"1015","name":"poiclosed"},{"type":"img","id":"1003","name":"icon"},{"type":"html","id":"1011","name":"traffic"},{"type":"text","id":"1014","name":"roadaoi"},{"type":"button","id":"1012","name":"ext_btn"},{"type":"webimg","id":"1009","value":"http://store.is.autonavi.com/showpic/8e17c1d1f5139aa05d34fb5dcfaee86a","name":"pic_info"},{"type":"img","id":"1008","name":"overbooked"},{"type":"img","id":"1007","name":"moreservice"},{"type":"html","id":"1006","name":"tag"},{"type":"text","id":"1005","value":"查看出入口、停车场","name":"parent_info"},{"pys":"23.106721","poiids":"B0FFH693QT","name":"parent_other_rel","pxs":"113.264572","childtype":"41","poiname":"广州市海珠区教育局停车场","address":"同福东路486号广州市海珠区教育局","shortname":"停车场","type":"array","id":"1004"},{"type":"text","id":"1010","name":"business_area"},{"type":"html","id":"1002","name":"deepinfo"},{"type":"html","id":"1001","name":"price"},{"type":"text","id":"1013","name":"aoi"}],"newtype":"130104","disp_name":"广州市海珠区教育局","cpdata":"","latitude":"23.106612","discount_flag":"0"}]
         */

        private int codepoint;
        private String code;
        private SuggestionBean suggestion;
        private String timestamp;
        private LqiiBean lqii;
        private String is_general_search;
        private String result;
        private String keywords;
        private String message;
        private String total;
        private String general_flag;
        private String bounds;
        private String version;
        private String busline_count;
        private MagicboxDataBean magicbox_data;
        private String interior_count;
        private List<?> busline_list;
        private List<?> bus_list;
        private List<PoiListBean> poi_list;

        public int getCodepoint() {
            return codepoint;
        }

        public void setCodepoint(int codepoint) {
            this.codepoint = codepoint;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public LqiiBean getLqii() {
            return lqii;
        }

        public void setLqii(LqiiBean lqii) {
            this.lqii = lqii;
        }

        public String getIs_general_search() {
            return is_general_search;
        }

        public void setIs_general_search(String is_general_search) {
            this.is_general_search = is_general_search;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getGeneral_flag() {
            return general_flag;
        }

        public void setGeneral_flag(String general_flag) {
            this.general_flag = general_flag;
        }

        public String getBounds() {
            return bounds;
        }

        public void setBounds(String bounds) {
            this.bounds = bounds;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getBusline_count() {
            return busline_count;
        }

        public void setBusline_count(String busline_count) {
            this.busline_count = busline_count;
        }

        public MagicboxDataBean getMagicbox_data() {
            return magicbox_data;
        }

        public void setMagicbox_data(MagicboxDataBean magicbox_data) {
            this.magicbox_data = magicbox_data;
        }

        public String getInterior_count() {
            return interior_count;
        }

        public void setInterior_count(String interior_count) {
            this.interior_count = interior_count;
        }

        public List<?> getBusline_list() {
            return busline_list;
        }

        public void setBusline_list(List<?> busline_list) {
            this.busline_list = busline_list;
        }

        public List<?> getBus_list() {
            return bus_list;
        }

        public void setBus_list(List<?> bus_list) {
            this.bus_list = bus_list;
        }

        public List<PoiListBean> getPoi_list() {
            return poi_list;
        }

        public void setPoi_list(List<PoiListBean> poi_list) {
            this.poi_list = poi_list;
        }

        public static class SuggestionBean {
        }

        public static class LqiiBean {
            /**
             * cache_directive : {"cache_filter":{"flag":"1","expires":"24"},"cache_all":{"flag":"0"}}
             * query_intent : {"cate_list":"","type":"1000","cate":"","cate_ext":""}
             * general_flag :
             * specialclassify : 0
             * view_region : 113.257124700,23.108984000,113.345448300,23.081828000
             * suggestcontent :
             * pdheatmap :
             * render_name_flag : 1
             * is_current_city : 2
             * expand_range_tip :
             * suggest_query : {"data":[],"col":"","row":""}
             * change_query_tip :
             * is_tupu_sug : 0
             * has_recommend : 0
             * brand_intent : {"t_tag":"","flag":"0","cate":"","name":""}
             * suggestionview : 1
             * change_query_type :
             * business :
             * car_icon_flag : 0
             * slayer_type : none
             * show_pic :
             * is_view_city : 1
             * querytype : 5
             * self_navigation :
             * showaoi :
             * utd_sceneid : 1000
             * distance_info :
             * slayer : 0
             * target_view_city :
             * call_taxi : 0
             * preload_next_page : 0
             * magic_box : {}
             * activity : {"new_year":{"flag":"0","tip":""}}
             */

            private CacheDirectiveBean cache_directive;
            private QueryIntentBean query_intent;
            private String general_flag;
            private String specialclassify;
            private String view_region;
            private String suggestcontent;
            private String pdheatmap;
            private String render_name_flag;
            private String is_current_city;
            private String expand_range_tip;
            private SuggestQueryBean suggest_query;
            private String change_query_tip;
            private String is_tupu_sug;
            private String has_recommend;
            private BrandIntentBean brand_intent;
            private String suggestionview;
            private String change_query_type;
            private String business;
            private String car_icon_flag;
            private String slayer_type;
            private String show_pic;
            private String is_view_city;
            private String querytype;
            private String self_navigation;
            private String showaoi;
            private String utd_sceneid;
            private String distance_info;
            private String slayer;
            private String target_view_city;
            private String call_taxi;
            private String preload_next_page;
            private MagicBoxBean magic_box;
            private ActivityBean activity;

            public CacheDirectiveBean getCache_directive() {
                return cache_directive;
            }

            public void setCache_directive(CacheDirectiveBean cache_directive) {
                this.cache_directive = cache_directive;
            }

            public QueryIntentBean getQuery_intent() {
                return query_intent;
            }

            public void setQuery_intent(QueryIntentBean query_intent) {
                this.query_intent = query_intent;
            }

            public String getGeneral_flag() {
                return general_flag;
            }

            public void setGeneral_flag(String general_flag) {
                this.general_flag = general_flag;
            }

            public String getSpecialclassify() {
                return specialclassify;
            }

            public void setSpecialclassify(String specialclassify) {
                this.specialclassify = specialclassify;
            }

            public String getView_region() {
                return view_region;
            }

            public void setView_region(String view_region) {
                this.view_region = view_region;
            }

            public String getSuggestcontent() {
                return suggestcontent;
            }

            public void setSuggestcontent(String suggestcontent) {
                this.suggestcontent = suggestcontent;
            }

            public String getPdheatmap() {
                return pdheatmap;
            }

            public void setPdheatmap(String pdheatmap) {
                this.pdheatmap = pdheatmap;
            }

            public String getRender_name_flag() {
                return render_name_flag;
            }

            public void setRender_name_flag(String render_name_flag) {
                this.render_name_flag = render_name_flag;
            }

            public String getIs_current_city() {
                return is_current_city;
            }

            public void setIs_current_city(String is_current_city) {
                this.is_current_city = is_current_city;
            }

            public String getExpand_range_tip() {
                return expand_range_tip;
            }

            public void setExpand_range_tip(String expand_range_tip) {
                this.expand_range_tip = expand_range_tip;
            }

            public SuggestQueryBean getSuggest_query() {
                return suggest_query;
            }

            public void setSuggest_query(SuggestQueryBean suggest_query) {
                this.suggest_query = suggest_query;
            }

            public String getChange_query_tip() {
                return change_query_tip;
            }

            public void setChange_query_tip(String change_query_tip) {
                this.change_query_tip = change_query_tip;
            }

            public String getIs_tupu_sug() {
                return is_tupu_sug;
            }

            public void setIs_tupu_sug(String is_tupu_sug) {
                this.is_tupu_sug = is_tupu_sug;
            }

            public String getHas_recommend() {
                return has_recommend;
            }

            public void setHas_recommend(String has_recommend) {
                this.has_recommend = has_recommend;
            }

            public BrandIntentBean getBrand_intent() {
                return brand_intent;
            }

            public void setBrand_intent(BrandIntentBean brand_intent) {
                this.brand_intent = brand_intent;
            }

            public String getSuggestionview() {
                return suggestionview;
            }

            public void setSuggestionview(String suggestionview) {
                this.suggestionview = suggestionview;
            }

            public String getChange_query_type() {
                return change_query_type;
            }

            public void setChange_query_type(String change_query_type) {
                this.change_query_type = change_query_type;
            }

            public String getBusiness() {
                return business;
            }

            public void setBusiness(String business) {
                this.business = business;
            }

            public String getCar_icon_flag() {
                return car_icon_flag;
            }

            public void setCar_icon_flag(String car_icon_flag) {
                this.car_icon_flag = car_icon_flag;
            }

            public String getSlayer_type() {
                return slayer_type;
            }

            public void setSlayer_type(String slayer_type) {
                this.slayer_type = slayer_type;
            }

            public String getShow_pic() {
                return show_pic;
            }

            public void setShow_pic(String show_pic) {
                this.show_pic = show_pic;
            }

            public String getIs_view_city() {
                return is_view_city;
            }

            public void setIs_view_city(String is_view_city) {
                this.is_view_city = is_view_city;
            }

            public String getQuerytype() {
                return querytype;
            }

            public void setQuerytype(String querytype) {
                this.querytype = querytype;
            }

            public String getSelf_navigation() {
                return self_navigation;
            }

            public void setSelf_navigation(String self_navigation) {
                this.self_navigation = self_navigation;
            }

            public String getShowaoi() {
                return showaoi;
            }

            public void setShowaoi(String showaoi) {
                this.showaoi = showaoi;
            }

            public String getUtd_sceneid() {
                return utd_sceneid;
            }

            public void setUtd_sceneid(String utd_sceneid) {
                this.utd_sceneid = utd_sceneid;
            }

            public String getDistance_info() {
                return distance_info;
            }

            public void setDistance_info(String distance_info) {
                this.distance_info = distance_info;
            }

            public String getSlayer() {
                return slayer;
            }

            public void setSlayer(String slayer) {
                this.slayer = slayer;
            }

            public String getTarget_view_city() {
                return target_view_city;
            }

            public void setTarget_view_city(String target_view_city) {
                this.target_view_city = target_view_city;
            }

            public String getCall_taxi() {
                return call_taxi;
            }

            public void setCall_taxi(String call_taxi) {
                this.call_taxi = call_taxi;
            }

            public String getPreload_next_page() {
                return preload_next_page;
            }

            public void setPreload_next_page(String preload_next_page) {
                this.preload_next_page = preload_next_page;
            }

            public MagicBoxBean getMagic_box() {
                return magic_box;
            }

            public void setMagic_box(MagicBoxBean magic_box) {
                this.magic_box = magic_box;
            }

            public ActivityBean getActivity() {
                return activity;
            }

            public void setActivity(ActivityBean activity) {
                this.activity = activity;
            }

            public static class CacheDirectiveBean {
                /**
                 * cache_filter : {"flag":"1","expires":"24"}
                 * cache_all : {"flag":"0"}
                 */

                private CacheFilterBean cache_filter;
                private CacheAllBean cache_all;

                public CacheFilterBean getCache_filter() {
                    return cache_filter;
                }

                public void setCache_filter(CacheFilterBean cache_filter) {
                    this.cache_filter = cache_filter;
                }

                public CacheAllBean getCache_all() {
                    return cache_all;
                }

                public void setCache_all(CacheAllBean cache_all) {
                    this.cache_all = cache_all;
                }

                public static class CacheFilterBean {
                    /**
                     * flag : 1
                     * expires : 24
                     */

                    private String flag;
                    private String expires;

                    public String getFlag() {
                        return flag;
                    }

                    public void setFlag(String flag) {
                        this.flag = flag;
                    }

                    public String getExpires() {
                        return expires;
                    }

                    public void setExpires(String expires) {
                        this.expires = expires;
                    }
                }

                public static class CacheAllBean {
                    /**
                     * flag : 0
                     */

                    private String flag;

                    public String getFlag() {
                        return flag;
                    }

                    public void setFlag(String flag) {
                        this.flag = flag;
                    }
                }
            }

            public static class QueryIntentBean {
                /**
                 * cate_list :
                 * type : 1000
                 * cate :
                 * cate_ext :
                 */

                private String cate_list;
                private String type;
                private String cate;
                private String cate_ext;

                public String getCate_list() {
                    return cate_list;
                }

                public void setCate_list(String cate_list) {
                    this.cate_list = cate_list;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getCate() {
                    return cate;
                }

                public void setCate(String cate) {
                    this.cate = cate;
                }

                public String getCate_ext() {
                    return cate_ext;
                }

                public void setCate_ext(String cate_ext) {
                    this.cate_ext = cate_ext;
                }
            }

            public static class SuggestQueryBean {
                /**
                 * data : []
                 * col :
                 * row :
                 */

                private String col;
                private String row;
                private List<?> data;

                public String getCol() {
                    return col;
                }

                public void setCol(String col) {
                    this.col = col;
                }

                public String getRow() {
                    return row;
                }

                public void setRow(String row) {
                    this.row = row;
                }

                public List<?> getData() {
                    return data;
                }

                public void setData(List<?> data) {
                    this.data = data;
                }
            }

            public static class BrandIntentBean {
                /**
                 * t_tag :
                 * flag : 0
                 * cate :
                 * name :
                 */

                private String t_tag;
                private String flag;
                private String cate;
                private String name;

                public String getT_tag() {
                    return t_tag;
                }

                public void setT_tag(String t_tag) {
                    this.t_tag = t_tag;
                }

                public String getFlag() {
                    return flag;
                }

                public void setFlag(String flag) {
                    this.flag = flag;
                }

                public String getCate() {
                    return cate;
                }

                public void setCate(String cate) {
                    this.cate = cate;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class MagicBoxBean {
            }

            public static class ActivityBean {
                /**
                 * new_year : {"flag":"0","tip":""}
                 */

                private NewYearBean new_year;

                public NewYearBean getNew_year() {
                    return new_year;
                }

                public void setNew_year(NewYearBean new_year) {
                    this.new_year = new_year;
                }

                public static class NewYearBean {
                    /**
                     * flag : 0
                     * tip :
                     */

                    private String flag;
                    private String tip;

                    public String getFlag() {
                        return flag;
                    }

                    public void setFlag(String flag) {
                        this.flag = flag;
                    }

                    public String getTip() {
                        return tip;
                    }

                    public void setTip(String tip) {
                        this.tip = tip;
                    }
                }
            }
        }

        public static class MagicboxDataBean {
        }

        public static class PoiListBean {
            /**
             * rating : 2
             * tel : 020-84417850
             * typecode : 130104
             * areacode : 020
             * cityname : 广州市
             * display_brand :
             * shape_region :
             * longitude : 113.264485
             * address : 同福东路486号
             * cinemazuo_flag : 0
             * diner_flag : 0
             * id : B00140DU7A
             * name : 广州市海珠区教育局
             * group_flag : 0
             * distance : 0
             * entrances : []
             * recommend_flag : 3
             * exits : []
             * adcode : 440105
             * domain_list : [{"type":"html","id":"1015","name":"poiclosed"},{"type":"img","id":"1003","name":"icon"},{"type":"html","id":"1011","name":"traffic"},{"type":"text","id":"1014","name":"roadaoi"},{"type":"button","id":"1012","name":"ext_btn"},{"type":"webimg","id":"1009","value":"http://store.is.autonavi.com/showpic/8e17c1d1f5139aa05d34fb5dcfaee86a","name":"pic_info"},{"type":"img","id":"1008","name":"overbooked"},{"type":"img","id":"1007","name":"moreservice"},{"type":"html","id":"1006","name":"tag"},{"type":"text","id":"1005","value":"查看出入口、停车场","name":"parent_info"},{"pys":"23.106721","poiids":"B0FFH693QT","name":"parent_other_rel","pxs":"113.264572","childtype":"41","poiname":"广州市海珠区教育局停车场","address":"同福东路486号广州市海珠区教育局","shortname":"停车场","type":"array","id":"1004"},{"type":"text","id":"1010","name":"business_area"},{"type":"html","id":"1002","name":"deepinfo"},{"type":"html","id":"1001","name":"price"},{"type":"text","id":"1013","name":"aoi"}]
             * newtype : 130104
             * disp_name : 广州市海珠区教育局
             * cpdata :
             * latitude : 23.106612
             * discount_flag : 0
             */

            private String rating;
            private String tel;
            private String typecode;
            private String areacode;
            private String cityname;
            private String display_brand;
            private String shape_region;
            private String longitude;
            private String address;
            private String cinemazuo_flag;
            private String diner_flag;
            private String id;
            private String name;
            private String group_flag;
            private String distance;
            private String recommend_flag;
            private String adcode;
            private String newtype;
            private String disp_name;
            private String cpdata;
            private String latitude;
            private String discount_flag;
            private List<?> entrances;
            private List<?> exits;
            private List<DomainListBean> domain_list;

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getTypecode() {
                return typecode;
            }

            public void setTypecode(String typecode) {
                this.typecode = typecode;
            }

            public String getAreacode() {
                return areacode;
            }

            public void setAreacode(String areacode) {
                this.areacode = areacode;
            }

            public String getCityname() {
                return cityname;
            }

            public void setCityname(String cityname) {
                this.cityname = cityname;
            }

            public String getDisplay_brand() {
                return display_brand;
            }

            public void setDisplay_brand(String display_brand) {
                this.display_brand = display_brand;
            }

            public String getShape_region() {
                return shape_region;
            }

            public void setShape_region(String shape_region) {
                this.shape_region = shape_region;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCinemazuo_flag() {
                return cinemazuo_flag;
            }

            public void setCinemazuo_flag(String cinemazuo_flag) {
                this.cinemazuo_flag = cinemazuo_flag;
            }

            public String getDiner_flag() {
                return diner_flag;
            }

            public void setDiner_flag(String diner_flag) {
                this.diner_flag = diner_flag;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getGroup_flag() {
                return group_flag;
            }

            public void setGroup_flag(String group_flag) {
                this.group_flag = group_flag;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getRecommend_flag() {
                return recommend_flag;
            }

            public void setRecommend_flag(String recommend_flag) {
                this.recommend_flag = recommend_flag;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getNewtype() {
                return newtype;
            }

            public void setNewtype(String newtype) {
                this.newtype = newtype;
            }

            public String getDisp_name() {
                return disp_name;
            }

            public void setDisp_name(String disp_name) {
                this.disp_name = disp_name;
            }

            public String getCpdata() {
                return cpdata;
            }

            public void setCpdata(String cpdata) {
                this.cpdata = cpdata;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getDiscount_flag() {
                return discount_flag;
            }

            public void setDiscount_flag(String discount_flag) {
                this.discount_flag = discount_flag;
            }

            public List<?> getEntrances() {
                return entrances;
            }

            public void setEntrances(List<?> entrances) {
                this.entrances = entrances;
            }

            public List<?> getExits() {
                return exits;
            }

            public void setExits(List<?> exits) {
                this.exits = exits;
            }

            public List<DomainListBean> getDomain_list() {
                return domain_list;
            }

            public void setDomain_list(List<DomainListBean> domain_list) {
                this.domain_list = domain_list;
            }

            public static class DomainListBean {
                /**
                 * type : html
                 * id : 1015
                 * name : poiclosed
                 * value : http://store.is.autonavi.com/showpic/8e17c1d1f5139aa05d34fb5dcfaee86a
                 * pys : 23.106721
                 * poiids : B0FFH693QT
                 * pxs : 113.264572
                 * childtype : 41
                 * poiname : 广州市海珠区教育局停车场
                 * address : 同福东路486号广州市海珠区教育局
                 * shortname : 停车场
                 */

                private String type;
                private String id;
                private String name;
                private String value;
                private String pys;
                private String poiids;
                private String pxs;
                private String childtype;
                private String poiname;
                private String address;
                private String shortname;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getPys() {
                    return pys;
                }

                public void setPys(String pys) {
                    this.pys = pys;
                }

                public String getPoiids() {
                    return poiids;
                }

                public void setPoiids(String poiids) {
                    this.poiids = poiids;
                }

                public String getPxs() {
                    return pxs;
                }

                public void setPxs(String pxs) {
                    this.pxs = pxs;
                }

                public String getChildtype() {
                    return childtype;
                }

                public void setChildtype(String childtype) {
                    this.childtype = childtype;
                }

                public String getPoiname() {
                    return poiname;
                }

                public void setPoiname(String poiname) {
                    this.poiname = poiname;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getShortname() {
                    return shortname;
                }

                public void setShortname(String shortname) {
                    this.shortname = shortname;
                }
            }
        }
    }
}
