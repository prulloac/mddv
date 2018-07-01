import Constants from './Constants'

const ifNotUndefined = (object, value) => (object === undefined ? '' : value)
const ifNotEmpty = (object, value) => (object.length === 0 ? '' : value)
const title = (append = '') => {
  document.title = Constants.APPLICATION_NAME
  if (append) {
    document.title += ` > ${append}`
  }
}

const functions = {
  ifNotUndefined,
  ifNotEmpty,
  title,
}

export default functions
export { ifNotUndefined }
export { ifNotEmpty }
export { title }
