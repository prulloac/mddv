const path = require('path')
const DirectoryNamedWebpackPlugin = require('directory-named-webpack-plugin')
const CleanWebpackPlugin = require('clean-webpack-plugin')
const ExtractTextPlugin = require('extract-text-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const DashboardPlugin = require('webpack-dashboard/plugin')

module.exports = {
  entry: {
    app: './src/index.js',
  },
  output: {
    filename: '[name].bundle.js',
    path: path.resolve(__dirname, 'dist'),
  },
  module: {
    rules: [
      {
        test: /\.(css|scss)$/,
        use: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: [
            'css-loader',
            'sass-loader',
          ],
        }),
      },
      {
        test: /\.(png|svg|jpg|gif)$/,
        loader: 'file-loader',
        options: {
          name: '[path][name].[ext]',
        },
      },
      {
        test: /\.(woff|woff2|eot|ttf|otf)$/,
        use: [
          'file-loader',
        ],
      },
      {
        enforce: 'pre',
        test: /\.js$/,
        exclude: /node_modules/,
        use: [
          'eslint-loader',
        ],
      },
      {
        test: /\.js$/,
        use: [
          'babel-loader',
        ],
        exclude: /node_modules/,
      },
    ],
  },
  resolve: {
    extensions: ['.js', '.jsx'],
    modules: [
      'src',
      'node_modules',
      path.resolve('.'),
    ],
    plugins: [
      new DirectoryNamedWebpackPlugin(true),
    ],
  },
  plugins: [
    new CleanWebpackPlugin(['dist']),
    new ExtractTextPlugin('styles.css'),
    new HtmlWebpackPlugin({
      template: './src/template.html',
    }),
    new DashboardPlugin(),
  ],
}
