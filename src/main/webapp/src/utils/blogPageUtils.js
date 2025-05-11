import { isEmpty } from '@/utils/utils';

export function getCoverImage( blogPage ) {
	if ( isEmpty(blogPage) !== true ) {

		if (isEmpty(blogPage.coverImage) === true) {
			return require('@/assets/blankcoverimage.png')
		} else {
			const data = atob( blogPage.coverImage.data );

			const byteArrays = [];

			for ( let i = 0; i < data.length; i++ ) {
				byteArrays.push( data.charCodeAt( i ) );
			}

			const ui8Array = new Uint8Array( byteArrays );

			const blob = new Blob( [ ui8Array ], { type: 'image/*' } );

			return URL.createObjectURL( blob );
		}
	}
}