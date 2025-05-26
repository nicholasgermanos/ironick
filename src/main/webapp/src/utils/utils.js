export function isEmpty(object) {
	return object === null || object === undefined || object.length === 0;
}

export function isEmptyCascade(object, list) {
	const objectIsEmpty = isEmpty(object)

	if (objectIsEmpty === true) {
		return true;
	}

	if (list.length === 0) {
		return objectIsEmpty
	}

	if ( isEmpty(object[list[0]]) !== true ) {
		return isEmptyCascade(object[list.shift()], list)
	}
}

export function getFormattedDate( timestamp ) {
	const date = new Date( timestamp );

	const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
	return date.toLocaleTimeString( 'en-AU', options );
}

export function isMobile() {
	return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test( navigator.userAgent );
}