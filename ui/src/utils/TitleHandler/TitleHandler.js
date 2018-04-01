import defaults from 'resources/defaults.json'

const setTitle = (newTitle = defaults.defaultWindowTitle) => {
  document.title = newTitle
}

const appendText = (appendedText = '') => document.title

module.exports = {
  SetTitle,

} 