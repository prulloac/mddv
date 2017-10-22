var gulp = require('gulp'),
    inject = require('gulp-inject'),
    bower = require('gulp-bower'),
    wiredep = require('wiredep').stream;



gulp.task('inject', function () {
    var target = gulp.src('./index.html');
    var sources = gulp.src(['./js/app.js','./css/**/*.css','./js/**/*.js']);
    return target.pipe(inject(sources, {relative: true}))
        .pipe(gulp.dest('./'));
});

gulp.task('bower', function () {
   return bower({
       directory: './bower_components'
   });
});

gulp.task('wiredep', function () {
    return gulp.src('./index.html')
        .pipe(wiredep({
            directory: './bower_components',
            bowerJson: require('./bower.json')
        }))
        .pipe(gulp.dest('./'));
});

gulp.task('init',['bower','wiredep']);
gulp.task('default',['init','inject']);