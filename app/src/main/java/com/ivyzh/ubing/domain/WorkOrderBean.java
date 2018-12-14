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
         * content : 谢谢
         * createdAt : 2018-12-14 05:30:50
         * objectId : 6ad470b42a
         * publishUserId : 21d739f9d7
         * state : 1
         * title : 求xx资源4
         * updatedAt : 2018-12-14 20:38:15
         */

        private String content;
        private String createdAt;
        private String objectId;
        private String publishUserId;
        private int state;
        private String title;
        private String updatedAt;

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
    }
}
