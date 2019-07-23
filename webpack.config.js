const HtmlWebpackPlugin = require('html-webpack-plugin');
const MonacoWebpackPlugin = require('monaco-editor-webpack-plugin');

module.exports = {
    mode: 'production',
    resolve: {
        extensions: ['*', '.js', '.vue', '.json']
    },
    entry: {
        app: ["./src/main/webapp/bootstrap1.js"]
    },
    module: {
        rules: [
            {
                test: /\.(sa|sc|c)ss$/,
                use: [
                    'style-loader',
                    'css-loader',
                    'sass-loader',
                ]
            },
            {
                test: /\.(woff(2)?|ttf|eot|svg)(\?v=\d+\.\d+\.\d+)?$/,
                use: {
                    loader: 'file-loader',
                    options: {
                        name: '[name].[ext]',
                        outputPath: 'fonts/'
                    }
                }
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            filename: './index.html',
            template: './src/main/webapp/index.html'
        }),
        new MonacoWebpackPlugin()
    ],
    output: {
        filename: '[name].js',
        publicPath: "/"
    },
    devServer: {
        port: 8080,
        historyApiFallback:true
    }
};