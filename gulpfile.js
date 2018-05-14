const gulp = require('gulp')
const prefixer = require('gulp-autoprefixer')
const sass = require('gulp-sass')
const plumber = require('gulp-plumber')


gulp.task('sass', () => {
    gulp.src('./web/assets/src/scss/*.scss')
            .pipe(plumber())
            .pipe(sass({
                outputStyle: 'expanded'
            }))
            .pipe(prefixer({
                browsers: ['last 4 versions']
            }))
            .pipe(gulp.dest('./web/assets/dist/css/'))
})


gulp.task('default', () =>{
    gulp.watch('./web/assets/src/scss/**/*.scss', ['sass'])
})