import FormField from '@/components/FormField/FormField';
import Button from '@components/atoms/Button/Button';
import './LogInForm.css';

const LogInForm = () => {
  return (
    <div className='login-form'>
      <div className='login-title'>
        <h2>Log in</h2>
        <p>Glad to see you again</p>
      </div>

      <div className='login-block'>
        <FormField
          label='Email'
          fieldType='input'
          id='email'
          type='email'
          placeholder='Write your email'
        />

        <FormField
          label='Password'
          fieldType='password'
          id='password'
          placeholder='Write your password'
        />
      </div>

      <Button text='Login' type='primary' />
      <p className='create-account-page'>
        New here? <a href='#'>Create an account</a>
      </p>
    </div>
  );
};

export default LogInForm;
