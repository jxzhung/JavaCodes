package com.t600.yingyuyujing.beans;

import java.util.List;

public class VideoInfoResp {

    /**
     * result : {"subtitle":{"packageId":0,"bindWords":[{"id":86470,"word":"mean","description":"adj.低劣的, 卑贱的, 简陋的, 吝啬的, 惭愧的, 平均的, 中间的, 普通的\nvt.意谓, 想要, 意欲, 预定\nvi.用意, 有意义\nn.平均数, 中间, 中庸\n[医]平均数, 均值\n[经]平均值, 平均数; 意谓","level":0,"usPhonetics":"min","ukPhonetics":"mi:n","hasUsVoice":-1,"hasUkVoice":-1,"bindTime":22923}],"subtitle":[{"videoId":9233,"indexNo":1,"startTime":21325,"endTime":22231,"subtitleCn":"对不起","subtitleEn":"I\u2019m sorry."},{"videoId":9233,"indexNo":2,"startTime":22231,"endTime":22975,"subtitleCn":"我对她没有\u2026\u2026","subtitleEn":"She doesn\u2019t mean..."},{"videoId":9233,"indexNo":3,"startTime":22975,"endTime":24400,"subtitleCn":"我对她什么意思都没有","subtitleEn":"She doesn\u2019t mean anything to me."},{"videoId":9233,"indexNo":4,"startTime":24400,"endTime":26075,"subtitleCn":"说啥呢？你干嘛喝我的饮料！","subtitleEn":"No, you took my drink."}],"videoId":9233},"videoInfo":{"id":9233,"duration":27,"sortIndex":0,"title":"你这叫做贼心虚","lastModifiedDateTime":"2018-04-04 10:00:28","accessTimes":107038,"accessType":0,"direction":1,"popularity":4164.547,"favoriteTimes":5168,"authorId":5471,"commentCount":4,"url":"https://vod1.cdn.meclass.com/201806081700/a133951626cd04b1374ef4492fb566c2/app/words_video/video/33/92/9233.mp4","coverUrl":"https://private1.cdn.meclass.com/public/app/words_video/video_cover/33/92/9233.jpg","favorited":1,"authorNickName":"英傍有戏精","authorLoginName":""},"videoStartTime":0}
     * code : 0
     */

    private ResultBean result;
    private int code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResultBean {
        /**
         * subtitle : {"packageId":0,"bindWords":[{"id":86470,"word":"mean","description":"adj.低劣的, 卑贱的, 简陋的, 吝啬的, 惭愧的, 平均的, 中间的, 普通的\nvt.意谓, 想要, 意欲, 预定\nvi.用意, 有意义\nn.平均数, 中间, 中庸\n[医]平均数, 均值\n[经]平均值, 平均数; 意谓","level":0,"usPhonetics":"min","ukPhonetics":"mi:n","hasUsVoice":-1,"hasUkVoice":-1,"bindTime":22923}],"subtitle":[{"videoId":9233,"indexNo":1,"startTime":21325,"endTime":22231,"subtitleCn":"对不起","subtitleEn":"I\u2019m sorry."},{"videoId":9233,"indexNo":2,"startTime":22231,"endTime":22975,"subtitleCn":"我对她没有\u2026\u2026","subtitleEn":"She doesn\u2019t mean..."},{"videoId":9233,"indexNo":3,"startTime":22975,"endTime":24400,"subtitleCn":"我对她什么意思都没有","subtitleEn":"She doesn\u2019t mean anything to me."},{"videoId":9233,"indexNo":4,"startTime":24400,"endTime":26075,"subtitleCn":"说啥呢？你干嘛喝我的饮料！","subtitleEn":"No, you took my drink."}],"videoId":9233}
         * videoInfo : {"id":9233,"duration":27,"sortIndex":0,"title":"你这叫做贼心虚","lastModifiedDateTime":"2018-04-04 10:00:28","accessTimes":107038,"accessType":0,"direction":1,"popularity":4164.547,"favoriteTimes":5168,"authorId":5471,"commentCount":4,"url":"https://vod1.cdn.meclass.com/201806081700/a133951626cd04b1374ef4492fb566c2/app/words_video/video/33/92/9233.mp4","coverUrl":"https://private1.cdn.meclass.com/public/app/words_video/video_cover/33/92/9233.jpg","favorited":1,"authorNickName":"英傍有戏精","authorLoginName":""}
         * videoStartTime : 0
         */

        private SubtitleBeanX subtitle;
        private VideoInfoBean videoInfo;
        private int videoStartTime;

        public SubtitleBeanX getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(SubtitleBeanX subtitle) {
            this.subtitle = subtitle;
        }

        public VideoInfoBean getVideoInfo() {
            return videoInfo;
        }

        public void setVideoInfo(VideoInfoBean videoInfo) {
            this.videoInfo = videoInfo;
        }

        public int getVideoStartTime() {
            return videoStartTime;
        }

        public void setVideoStartTime(int videoStartTime) {
            this.videoStartTime = videoStartTime;
        }

        public static class SubtitleBeanX {
            /**
             * packageId : 0
             * bindWords : [{"id":86470,"word":"mean","description":"adj.低劣的, 卑贱的, 简陋的, 吝啬的, 惭愧的, 平均的, 中间的, 普通的\nvt.意谓, 想要, 意欲, 预定\nvi.用意, 有意义\nn.平均数, 中间, 中庸\n[医]平均数, 均值\n[经]平均值, 平均数; 意谓","level":0,"usPhonetics":"min","ukPhonetics":"mi:n","hasUsVoice":-1,"hasUkVoice":-1,"bindTime":22923}]
             * subtitle : [{"videoId":9233,"indexNo":1,"startTime":21325,"endTime":22231,"subtitleCn":"对不起","subtitleEn":"I\u2019m sorry."},{"videoId":9233,"indexNo":2,"startTime":22231,"endTime":22975,"subtitleCn":"我对她没有\u2026\u2026","subtitleEn":"She doesn\u2019t mean..."},{"videoId":9233,"indexNo":3,"startTime":22975,"endTime":24400,"subtitleCn":"我对她什么意思都没有","subtitleEn":"She doesn\u2019t mean anything to me."},{"videoId":9233,"indexNo":4,"startTime":24400,"endTime":26075,"subtitleCn":"说啥呢？你干嘛喝我的饮料！","subtitleEn":"No, you took my drink."}]
             * videoId : 9233
             */

            private int packageId;
            private int videoId;
            private List<BindWordsBean> bindWords;
            private List<SubtitleBean> subtitle;

            public int getPackageId() {
                return packageId;
            }

            public void setPackageId(int packageId) {
                this.packageId = packageId;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public List<BindWordsBean> getBindWords() {
                return bindWords;
            }

            public void setBindWords(List<BindWordsBean> bindWords) {
                this.bindWords = bindWords;
            }

            public List<SubtitleBean> getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(List<SubtitleBean> subtitle) {
                this.subtitle = subtitle;
            }

            public static class BindWordsBean {
                /**
                 * id : 86470
                 * word : mean
                 * description : adj.低劣的, 卑贱的, 简陋的, 吝啬的, 惭愧的, 平均的, 中间的, 普通的
                 vt.意谓, 想要, 意欲, 预定
                 vi.用意, 有意义
                 n.平均数, 中间, 中庸
                 [医]平均数, 均值
                 [经]平均值, 平均数; 意谓
                 * level : 0
                 * usPhonetics : min
                 * ukPhonetics : mi:n
                 * hasUsVoice : -1
                 * hasUkVoice : -1
                 * bindTime : 22923
                 */

                private int id;
                private String word;
                private String description;
                private int level;
                private String usPhonetics;
                private String ukPhonetics;
                private int hasUsVoice;
                private int hasUkVoice;
                private int bindTime;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getWord() {
                    return word;
                }

                public void setWord(String word) {
                    this.word = word;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getUsPhonetics() {
                    return usPhonetics;
                }

                public void setUsPhonetics(String usPhonetics) {
                    this.usPhonetics = usPhonetics;
                }

                public String getUkPhonetics() {
                    return ukPhonetics;
                }

                public void setUkPhonetics(String ukPhonetics) {
                    this.ukPhonetics = ukPhonetics;
                }

                public int getHasUsVoice() {
                    return hasUsVoice;
                }

                public void setHasUsVoice(int hasUsVoice) {
                    this.hasUsVoice = hasUsVoice;
                }

                public int getHasUkVoice() {
                    return hasUkVoice;
                }

                public void setHasUkVoice(int hasUkVoice) {
                    this.hasUkVoice = hasUkVoice;
                }

                public int getBindTime() {
                    return bindTime;
                }

                public void setBindTime(int bindTime) {
                    this.bindTime = bindTime;
                }
            }

            public static class SubtitleBean {
                /**
                 * videoId : 9233
                 * indexNo : 1
                 * startTime : 21325
                 * endTime : 22231
                 * subtitleCn : 对不起
                 * subtitleEn : I’m sorry.
                 */

                private int videoId;
                private int indexNo;
                private int startTime;
                private int endTime;
                private String subtitleCn;
                private String subtitleEn;

                public int getVideoId() {
                    return videoId;
                }

                public void setVideoId(int videoId) {
                    this.videoId = videoId;
                }

                public int getIndexNo() {
                    return indexNo;
                }

                public void setIndexNo(int indexNo) {
                    this.indexNo = indexNo;
                }

                public int getStartTime() {
                    return startTime;
                }

                public void setStartTime(int startTime) {
                    this.startTime = startTime;
                }

                public int getEndTime() {
                    return endTime;
                }

                public void setEndTime(int endTime) {
                    this.endTime = endTime;
                }

                public String getSubtitleCn() {
                    return subtitleCn;
                }

                public void setSubtitleCn(String subtitleCn) {
                    this.subtitleCn = subtitleCn;
                }

                public String getSubtitleEn() {
                    return subtitleEn;
                }

                public void setSubtitleEn(String subtitleEn) {
                    this.subtitleEn = subtitleEn;
                }
            }
        }

        public static class VideoInfoBean {
            /**
             * id : 9233
             * duration : 27
             * sortIndex : 0
             * title : 你这叫做贼心虚
             * lastModifiedDateTime : 2018-04-04 10:00:28
             * accessTimes : 107038
             * accessType : 0
             * direction : 1
             * popularity : 4164.547
             * favoriteTimes : 5168
             * authorId : 5471
             * commentCount : 4
             * url : https://vod1.cdn.meclass.com/201806081700/a133951626cd04b1374ef4492fb566c2/app/words_video/video/33/92/9233.mp4
             * coverUrl : https://private1.cdn.meclass.com/public/app/words_video/video_cover/33/92/9233.jpg
             * favorited : 1
             * authorNickName : 英傍有戏精
             * authorLoginName :
             */

            private int id;
            private int duration;
            private int sortIndex;
            private String title;
            private String lastModifiedDateTime;
            private int accessTimes;
            private int accessType;
            private int direction;
            private double popularity;
            private int favoriteTimes;
            private int authorId;
            private int commentCount;
            private String url;
            private String coverUrl;
            private int favorited;
            private String authorNickName;
            private String authorLoginName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getSortIndex() {
                return sortIndex;
            }

            public void setSortIndex(int sortIndex) {
                this.sortIndex = sortIndex;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLastModifiedDateTime() {
                return lastModifiedDateTime;
            }

            public void setLastModifiedDateTime(String lastModifiedDateTime) {
                this.lastModifiedDateTime = lastModifiedDateTime;
            }

            public int getAccessTimes() {
                return accessTimes;
            }

            public void setAccessTimes(int accessTimes) {
                this.accessTimes = accessTimes;
            }

            public int getAccessType() {
                return accessType;
            }

            public void setAccessType(int accessType) {
                this.accessType = accessType;
            }

            public int getDirection() {
                return direction;
            }

            public void setDirection(int direction) {
                this.direction = direction;
            }

            public double getPopularity() {
                return popularity;
            }

            public void setPopularity(double popularity) {
                this.popularity = popularity;
            }

            public int getFavoriteTimes() {
                return favoriteTimes;
            }

            public void setFavoriteTimes(int favoriteTimes) {
                this.favoriteTimes = favoriteTimes;
            }

            public int getAuthorId() {
                return authorId;
            }

            public void setAuthorId(int authorId) {
                this.authorId = authorId;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCoverUrl() {
                return coverUrl;
            }

            public void setCoverUrl(String coverUrl) {
                this.coverUrl = coverUrl;
            }

            public int getFavorited() {
                return favorited;
            }

            public void setFavorited(int favorited) {
                this.favorited = favorited;
            }

            public String getAuthorNickName() {
                return authorNickName;
            }

            public void setAuthorNickName(String authorNickName) {
                this.authorNickName = authorNickName;
            }

            public String getAuthorLoginName() {
                return authorLoginName;
            }

            public void setAuthorLoginName(String authorLoginName) {
                this.authorLoginName = authorLoginName;
            }
        }
    }
}
