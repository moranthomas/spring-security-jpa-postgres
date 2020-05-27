class Main:
    def launchAndDebug(self):
        try:
            print("Goodbye, World!")
            screen_message = "Goodbye, World!"
        except (BaseException, Exception, ImportError, TypeError) as e:
            stacktrace = stacktrace + ' \n \n Custom Exception:\n' + str(e)
            print('Exception Handled')
        return screen_message
