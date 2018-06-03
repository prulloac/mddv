import Constants from './Constants'

const documentTitle = (append = '') => {
  document.title = Constants.APPLICATION_NAME
  if (append) {
    document.title += ` > ${append}`
  }
}

export default documentTitle
