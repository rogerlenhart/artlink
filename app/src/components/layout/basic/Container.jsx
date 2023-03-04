import classNames from 'classnames'

export function Container({ children, className }) {

  return (
    <div className={classNames('w-full h-full max-w-7xl flex items-center justify-center', {
      [className]: className
    })}>
      {children}
    </div>
  )
}