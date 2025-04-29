import axios from 'axios';

// No trailing slashes here
const BLOG_PAGE_API_BASE_URL = 'http://localhost:8080/api/blogPage';
const BLOG_PAGE_API_ADD_PAGE_URL = 'http://localhost:8080/api/addBlogPage';

// Trailing slashes here for appending
const BLOG_PAGE_API_GET_PAGE_URL = 'http://localhost:8080/api/getBlogPage/';
const BLOG_PAGE_API_DELETE_PAGE_URL = 'http://localhost:8080/api/deleteBlogPage/';
const BLOG_PAGE_API_ADD_COVER_IMAGE_URL = 'http://localhost:8080/api/addCoverImage/';

class BlogPageService {
	getBlogPages() {
		return axios.get( BLOG_PAGE_API_BASE_URL );
	}

	addBlogPage( data, coverImage ) {

		axios.post( BLOG_PAGE_API_ADD_PAGE_URL, data )
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
}
//
// axios.defaults.withCredentials = true;
// axios.defaults.withXSRFToken = true;

export default new BlogPageService();