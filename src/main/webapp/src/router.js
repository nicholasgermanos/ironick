import { createWebHistory, createRouter } from 'vue-router';

import HomePage from '@/views/HomePage.vue';
import NewEntryForm from '@/views/NewEntryForm.vue';
import BlogEntries from '@/views/BlogEntries.vue';
import BlogPage from '@/views/BlogPage.vue';
import LoginPage from '@/views/LoginPage.vue';
import RegisterPage from '@/views/RegisterPage.vue';
import LogoutPage from '@/views/LogoutPage.vue';
import ContactCard from '@/views/ContactCard.vue'
import ContributeMenu from '@/views/ContributeMenu.vue';
import DraftsPage from '@/views/DraftsPage.vue';
import PublishedPage from '@/views/PublishedPage.vue';

const routes = [
	{ name: 'root', path: '/', component: HomePage },
	{ name: 'login', path: '/login', component: LoginPage },
	{ name: 'logout', path: '/logout', component: LogoutPage },
	{ name: 'register', path: '/register', component: RegisterPage },
	{ name: 'entryFormNew', path: '/newEntryForm', component: NewEntryForm, props: true },
	{ name: 'entryFormEdit', path: '/newEntryForm/:blogID', component: NewEntryForm, props: true },
	{ name: 'blogs', path: '/blogs', component: BlogEntries },
	{ name: 'blog', path: '/blog/:blogID', component: BlogPage, props: true },
	{ name: 'contact', path: '/contact', component: ContactCard },
	{ name: 'contribute', path: '/contribute', component: ContributeMenu},
	{ name: 'drafts', path: '/drafts', component: DraftsPage},
	{ name: 'published', path: '/published', component: PublishedPage},

];

const router = createRouter( {
	history: createWebHistory(),
	scrollBehavior() {
		return {
			top: 0
		}
	},
	routes
} );

router.beforeEach((to, from, next) => {
	const publicPages = ['root', 'login', 'register', 'blogs', 'blog', 'contact' ];
	const authRequired = !publicPages.includes(to.name);
	const loggedIn = localStorage.getItem('user');

	// trying to access a restricted page + not logged in
	// redirect to login page
	if (authRequired && !loggedIn) {
		next('/login');
	} else {
		next();
	}
});

export default router;
