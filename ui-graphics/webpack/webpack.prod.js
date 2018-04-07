const merge = require('webpack-merge')
const UglifyJsPlugin = require('uglifyjs-webpack-plugin')
const baseConfig = require('./webpack.base')

const config = merge(baseConfig, {
  plugins: [
    new UglifyJsPlugin({ test: /\.js($|\?)/i, sourceMap: false }),
  ],
  mode: 'production',
})

module.exports = config
