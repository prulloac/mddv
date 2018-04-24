const merge = require('webpack-merge')
const baseConfig = require('./webpack.base')

const config = merge(baseConfig, {
  devtool: 'source-map',
  devServer: {
    contentBase: './dist',
    port: 9999,
  },
  mode: 'development',
})

module.exports = config
