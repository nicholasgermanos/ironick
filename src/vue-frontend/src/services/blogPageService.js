import axios from 'axios';

// No trailing slashes here
const BLOG_PAGE_API_BASE_URL = 'http://localhost:8080/api/blogPage';
const BLOG_PAGE_API_GET_FEATURED_URL = 'http://localhost:8080/api/blogPagesFeatured';
const BLOG_PAGE_API_GET_UN_FEATURED_URL = 'http://localhost:8080/api/blogPagesUnFeatured';

// Trailing slashes here for appending
const BLOG_PAGE_API_ADD_PAGE_URL = 'http://localhost:8080/api/addBlogPage/';
const BLOG_PAGE_API_GET_PAGE_URL = 'http://localhost:8080/api/getBlogPage/';
const BLOG_PAGE_API_DELETE_PAGE_URL = 'http://localhost:8080/api/deleteBlogPage/';
const BLOG_PAGE_API_ADD_COVER_IMAGE_URL = 'http://localhost:8080/api/addCoverImage/';
const BLOG_PAGE_API_FEATURE_URL = 'http://localhost:8080/api/markBlogPageFeatured/';
const BLOG_PAGE_API_UN_FEATURE_URL = 'http://localhost:8080/api/markBlogPageNotFeatured/';

class BlogPageService {
	getBlogPages( getFeatured, getUnFeatured ) {
		if (getFeatured === true) {
			return this.getFeaturedBlogPages();
		}

		if (getUnFeatured === true) {
			return this.getUnFeaturedBlogPages();
		}

		return axios.get( BLOG_PAGE_API_BASE_URL );
	}

	getFeaturedBlogPages() {
		return axios.get( BLOG_PAGE_API_GET_FEATURED_URL );
	}

	getUnFeaturedBlogPages() {
		return axios.get( BLOG_PAGE_API_GET_UN_FEATURED_URL );
	}

	addBlogPage( data, userID, coverImage ) {

		axios.post( BLOG_PAGE_API_ADD_PAGE_URL + userID, data )
		.then( function( response ) {
				if ( coverImage != null && response.data != null ) {
					const formData = new FormData();
					formData.append('image', coverImage);
					axios.post( BLOG_PAGE_API_ADD_COVER_IMAGE_URL + response.data.id, formData ).then();
				}
			},
		).catch(function( error ) {
			alert(error)
		});
	}

	deleteBlogPage( id ) {
		axios.post( BLOG_PAGE_API_DELETE_PAGE_URL + id ).then();
	}

	getBlogPage( id ) {
		return axios.get( BLOG_PAGE_API_GET_PAGE_URL + id ).then();
	}

	featureBlogPage(id) {
		axios.post( BLOG_PAGE_API_FEATURE_URL + id ).then();
	}

	unFeatureBlogPage(id) {
		axios.post( BLOG_PAGE_API_UN_FEATURE_URL + id ).then();
	}
}
//
// axios.defaults.withCredentials = true;
// axios.defaults.withXSRFToken = true;

export default new BlogPageService();