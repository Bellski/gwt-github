const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    mode: 'production',
    resolve: {
        extensions: ['*', '.js', '.vue', '.json']
    },
    entry: {
        app: ["./src/main/webapp/bootstrap.js"]
    },
    plugins: [
        new HtmlWebpackPlugin({
            filename: './index.html',
            template: './src/main/webapp/index.html'
        })
    ],
    output: {
        filename: '[name].[contenthash].js'
    }
};