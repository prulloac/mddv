const merge = require('webpack-merge')
const baseConfig = require('./webpack.base')
const BabelMinifyWebpackPlugin = require('babel-minify-webpack-plugin')

const config = merge(baseConfig, {
  plugins: [
    new BabelMinifyWebpackPlugin({ booleans: true, removeConsole: true }, { test: /\.js($|\?)/i, sourceMap: false }),
  ],
  mode: 'production',
})

module.exports = config
