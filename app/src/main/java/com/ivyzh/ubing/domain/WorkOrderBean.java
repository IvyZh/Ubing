package com.ivyzh.ubing.domain;

import java.util.List;

/**
 * Created by Ivy on 2018/12/14.
 */

public class WorkOrderBean {


    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * content : 跪求啊，四级快来了，求大佬们解救一下
         * createdAt : 2018-12-15 20:50:29
         * objectId : 2eaa337526
         * publishUserId : 05fc04feef
         * state : 1
         * title : 求2018年12月的考虫英语四级视频讲义
         * updatedAt : 2018-12-15 20:54:03
         * author : {"__type":"Object","className":"_User","createdAt":"2018-12-14 05:13:05","gender":0,"nickname":"Admin","objectId":"21d739f9d7","portraitUrl":"http://img3.duitang.com/uploads/item/201408/19/20140819194253_tia5Y.jpeg","updatedAt":"2018-12-15 20:35:45","username":"zz"}
         */

        private String content;
        private String createdAt;
        private String objectId;
        private String publishUserId;
        private int state;
        private String title;
        private String updatedAt;
        private AuthorBean author;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getPublishUserId() {
            return publishUserId;
        }

        public void setPublishUserId(String publishUserId) {
            this.publishUserId = publishUserId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public static class AuthorBean {
            /**
             * __type : Object
             * className : _User
             * createdAt : 2018-12-14 05:13:05
             * gender : 0
             * nickname : Admin
             * objectId : 21d739f9d7
             * portraitUrl : http://img3.duitang.com/uploads/item/201408/19/20140819194253_tia5Y.jpeg
             * updatedAt : 2018-12-15 20:35:45
             * username : zz
             */

            private String __type;
            private String className;
            private String createdAt;
            private int gender;
            private String nickname;
            private String objectId;
            private String portraitUrl;
            private String updatedAt;
            private String username;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public String getPortraitUrl() {
                return portraitUrl;
            }

            public void setPortraitUrl(String portraitUrl) {
                this.portraitUrl = portraitUrl;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
