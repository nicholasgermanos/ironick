import axios from 'axios';
import { isEmpty } from '@/utils/utils';

axios.defaults.baseURL = '/api/';

// No trailing slashes here
const BLOG_PAGE_API_BASE_URL = 'blogPage';
const BLOG_PAGE_API_GET_FEATURED_URL = 'blogPagesFeatured';
const BLOG_PAGE_API_GET_UN_FEATURED_URL = 'blogPagesUnFeatured';

// Trailing slashes here for appending
const BLOG_PAGE_API_ADD_PAGE_URL = 'addBlogPage/';
const BLOG_PAGE_API_GET_PAGE_URL = 'getBlogPage/';
const BLOG_PAGE_API_DELETE_PAGE_URL = 'deleteBlogPage/';
const BLOG_PAGE_API_ADD_COVER_IMAGE_URL = 'addCoverImage/';
const BLOG_PAGE_API_FEATURE_URL = 'markBlogPageFeatured/';
const BLOG_PAGE_API_UN_FEATURE_URL = 'markBlogPageNotFeatured/';
const BLOG_PAGE_API_PUBLISH_URL= 'publish/';
const BLOG_PAGE_API_UNPUBLISH_URL= 'unpublish/';
const BLOG_PAGE_API_GET_DRAFTS_URL = 'drafts/'

class BlogPageService {
	getBlogPages( getFeatured, getUnFeatured ) {
		if (getFeatured === true) {
			return this.getFeaturedBlogPages()
		}

		if (getUnFeatured === true) {
			return this.getUnFeaturedBlogPages()
		}

		return axios.get( BLOG_PAGE_API_BASE_URL )
	}

	getDrafts(userId) {
		return axios.get( BLOG_PAGE_API_GET_DRAFTS_URL + userId )
	}
	getFeaturedBlogPages() {
		return axios.get( BLOG_PAGE_API_GET_FEATURED_URL )
	}

	getUnFeaturedBlogPages() {
		return axios.get( BLOG_PAGE_API_GET_UN_FEATURED_URL )
	}

	addBlogPage( data, userID, coverImage ) {
		return axios.post( BLOG_PAGE_API_ADD_PAGE_URL + userID, data )
		.then( ( response ) => {
				if ( coverImage != null && response.data != null ) {
					const formData = new FormData();
					formData.append('image', coverImage);
					axios.post( BLOG_PAGE_API_ADD_COVER_IMAGE_URL + response.data.id, formData ).then()
				}
				return response
			},
		).catch(function( error ) {
			alert(error)
		});
	}

	deleteBlogPage( id ) {
		axios.post( BLOG_PAGE_API_DELETE_PAGE_URL + id ).then()
	}

	getBlogPage( id ) {
		return axios.get( BLOG_PAGE_API_GET_PAGE_URL + id ).then()
	}

	featureBlogPage(id) {
		axios.post( BLOG_PAGE_API_FEATURE_URL + id ).then()
	}

	unFeatureBlogPage(id) {
		axios.post( BLOG_PAGE_API_UN_FEATURE_URL + id ).then()
	}
	publishBlogPage(id) {
		axios.post(BLOG_PAGE_API_PUBLISH_URL + id).then( (response) => {
			if (isEmpty(response) === false) {
				alert("Post is now live")
			}
		})
	}
	unPublishBlogPage(id) {
		axios.post(BLOG_PAGE_API_UNPUBLISH_URL + id).then((response) => {
			if (isEmpty(response) === false) {
				alert("Post is now in drafts")
			}
		})
	}
}

export default new BlogPageService();