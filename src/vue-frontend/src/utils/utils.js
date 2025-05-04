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