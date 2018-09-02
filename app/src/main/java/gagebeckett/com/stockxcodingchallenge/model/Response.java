package gagebeckett.com.stockxcodingchallenge.model;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private String kind;
    private ResponseData data;

    public ResponseData getData() {
        return data;
    }

    public static class ResponseData {
        private String modhash;
        private int dist;
        private List<ResponseChildData> children;
        private String after;
        private String before;

        public List<ResponseChildData> getChildren() {
            return children;
        }
    }

    public static class ResponseChildData {
        private String kind;
        private ChildData data;

        public ChildData getData() {
            return data;
        }
    }

    public static class ChildData {
        private String subreddit;
        private String subreddit_type;
        private String domain;
        private String selftext;
        private String author_fullname;
        private String link_flair_background_color;
        private String title;
        private String subreddit_name_prefixed;
        private String author;
        private String id;
        private String link_flair_text_color;
        private String thumbnail;
        private String link_flair_type;
        private String post_hint;
        private String name;
        private String subreddit_id;
        private String url;
        private String permalink;
        private String parent_whitelist_status;
        private String whitelist_status;
        private String author_flair_type;

        private boolean saved;
        private boolean clicked;
        private boolean hidden;
        private boolean hide_score;
        private boolean quarantine;
        private boolean is_original_content;
        private boolean is_reddit_media_domain;
        private boolean is_meta;
        private boolean can_mod_post;
        private boolean edited;
        private boolean is_self;
        private boolean contest_mode;
        private boolean archived;
        private boolean no_follow;
        private boolean is_crosspostable;
        private boolean pinned;
        private boolean over_18;
        private boolean media_only;
        private boolean can_gild;
        private boolean spoiler;
        private boolean locked;
        private boolean visited;
        private boolean send_replies;
        private boolean stickied;
        private boolean is_video;
        private boolean enabled;

        private ArrayList link_flair_richtext;
        private ArrayList user_reports;
        private ArrayList author_flair_richtext;
        private ArrayList images;
        private ArrayList resolutions;
        private ArrayList mod_reports;

        private Object media_embed;
        private Object secure_media_embed;
        private Object preview;
        private Object varients;
        private Object source;

        private int gilded;
        private int pwls;
        private int downs;
        private int thumbnail_height;
        private int ups;
        private int thumbnail_width;
        private int num_crossposts;
        private int num_comments;
        private int created;
        private int wls;
        private int created_utc;
        private int score;
        private int subreddit_subscribers;

        public String getTitle() {
            return title;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public String getSubreddit_name_prefixed() {
            return subreddit_name_prefixed;
        }

        public String getPermalink() {
            return permalink;
        }

        public String getAuthor() {
            return author;
        }

        public String getUps() {
            return Integer.toString(ups);
        }

    }
}
/*
"data"{
				"approved_at_utc": null,
				"mod_reason_title": null,
				"link_flair_css_class": null,
				"author_flair_background_color": null,
				"author_flair_template_id": null,
				"link_flair_text": null,
				"author_flair_css_class": null,
				"approved_by": null,
				"secure_media": null,
				"mod_note": null,
				"author_flair_text": null,
				"content_categories": null,
				"banned_by": null,
				"author_flair_text_color": null,
				"selftext_html": null,
				"media": null,
				"likes": null,
				"report_reasons": null,
				"suggested_sort": null,
				"link_flair_template_id": null,
				"banned_at_utc": null,
				"num_reports": null,
				"mod_reason_by": null,
				"removal_reason": null,
				"distinguished": null,
				"view_count": null,
				"category": null,

				****OBJECTS******
				"media_embed": {},
				"secure_media_embed": {},
				"preview": {
					"images": [{
						"source": {
							"url": "https://i.redditmedia.com/gFq5EE_O-blBZbNYnfDRnTTwAHiubXUPpP-Iqywp_hA.png?s=d70ad93f46d9d24de6ac2b03af58588a",
							"width": 998,
							"height": 943
						},
						"resolutions": [{
							"url": "https://i.redditmedia.com/gFq5EE_O-blBZbNYnfDRnTTwAHiubXUPpP-Iqywp_hA.png?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=108&amp;s=1dc51a0f48e07cb43882db2dcf7fdb00",
							"width": 108,
							"height": 102
						}, {
							"url": "https://i.redditmedia.com/gFq5EE_O-blBZbNYnfDRnTTwAHiubXUPpP-Iqywp_hA.png?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=216&amp;s=3031973e5e7fd7fb26bb4b743c68cb7f",
							"width": 216,
							"height": 204
						}, {
							"url": "https://i.redditmedia.com/gFq5EE_O-blBZbNYnfDRnTTwAHiubXUPpP-Iqywp_hA.png?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;s=76895f66249cb7faf58891d097b48653",
							"width": 320,
							"height": 302
						}, {
							"url": "https://i.redditmedia.com/gFq5EE_O-blBZbNYnfDRnTTwAHiubXUPpP-Iqywp_hA.png?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=640&amp;s=5ece82ad71b84057e9f22e22715d271d",
							"width": 640,
							"height": 604
						}, {
							"url": "https://i.redditmedia.com/gFq5EE_O-blBZbNYnfDRnTTwAHiubXUPpP-Iqywp_hA.png?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=960&amp;s=f16f0668c0821d8adb93ff0ad7281412",
							"width": 960,
							"height": 907
						}],
						"variants": {},
						"id": "J71XVFp7wvnfbgwjnyLQlgY_wDb00Sppz_ygYI0jn_U"
					}],
					"enabled": true
				},

				****STRING****
				"subreddit": "ProgrammerHumor",
				"subreddit_type": "public",
				"domain": "i.redd.it",
				"selftext": "",
				"author_fullname": "t2_73ce7",
				"link_flair_background_color": "",
				"title": "Some probably proposed this and got shut down by the marketing team.",
				"subreddit_name_prefixed": "r/ProgrammerHumor",
				"author": "JackDragon",
				"id": "9btb1h",
				"link_flair_text_color": "dark",
				"thumbnail": "https://a.thumbs.redditmedia.com/eQvQLXvWu54b4dVWpqn7O5u43m0GCpI3din2PdxiWL0.jpg",
				"link_flair_type": "text",
				"post_hint": "image",
				"name": "t3_9btb1h",
				"subreddit_id": "t5_2tex6",
				"url": "https://i.redd.it/ekxxnn4s6fj11.png",
				"permalink": "/r/ProgrammerHumor/comments/9btb1h/some_probably_proposed_this_and_got_shut_down_by/",
				"parent_whitelist_status": "all_ads",
				"whitelist_status": "all_ads",
				"author_flair_type": "text",

				****INTS******
				"gilded": 0,
				"pwls": 6,
				"downs": 0,
				"thumbnail_height": 132,
				"ups": 5711,
				"thumbnail_width": 140,
				"num_crossposts": 0,
				"num_comments": 49,
				"created": 1535748428.0,
				"wls": 6,
				"created_utc": 1535719628.0,
				"score": 5711,
				"subreddit_subscribers": 606494,

				*****LISTS******
				"link_flair_richtext": [],
				"author_flair_richtext": [],
				"user_reports": [],
				"mod_reports": [],

				*****BOOLEAN*****
				"saved": false,
				"clicked": false,
				"hidden": false,
				"hide_score": false,
				"quarantine": false,
				"is_original_content": false,
				"is_reddit_media_domain": true,
				"is_meta": false,
				"can_mod_post": false,
				"edited": false,
				"is_self": false,
				"contest_mode": false,
				"archived": false,
				"no_follow": false,
				"is_crosspostable": false,
				"pinned": false,
				"over_18": false,
				"media_only": false,
				"can_gild": false,
				"spoiler": false,
				"locked": false,
				"visited": false,
				"send_replies": false,
				"stickied": false,
				"is_video": false

			}
 */