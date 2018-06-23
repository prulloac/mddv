const ifNotUndefined = (object, value) => (object === undefined ? '' : value)
const ifNotEmpty = (object, value) => (object.length === 0 ? '' : value)

const functions = {
  ifNotUndefined,
  ifNotEmpty,
}

export default functions
export { ifNotUndefined }
export { ifNotEmpty }
