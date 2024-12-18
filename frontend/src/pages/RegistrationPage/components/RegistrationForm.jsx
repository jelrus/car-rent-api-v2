import FormField from '@/components/FormField/FormField';
import Button from '@components/atoms/Button/Button';
import './RegistrationForm.css';

const RegistrationForm = () => {
    return (
        <div className='registration-form'>
            <div className='registration-form__title'>
                <h2>Create an account</h2>
                <p>Enter your details below to get started</p>
            </div>

            <div className='registration-form__block'>
                
                <div className='registration-form__block-name'>
                    <FormField 
                        id = 'name'
                        type = 'text'
                        placeholder = 'Write your name'
                        fieldType='input'
                        label = 'Name'
                    />

                    <FormField 
                        id = 'surname'
                        type = 'text'
                        placeholder = 'Write your surname'
                        fieldType='input'
                        label = 'Surname'
                    />
                </div>
                
                <FormField 
                    id = 'email'
                    type = 'email'
                    placeholder = 'Write your email'
                    fieldType='input'
                    label = 'Email'
                />

                <FormField 
                    id = 'password'
                    type = 'email'
                    placeholder = 'Create password'
                    fieldType='password'
                    label = 'Password'
                />

                <div className='registration-form__block-button'>
                    <Button text='Cancel' type='secondary' />
                    <Button text='Register' type='primary' />
                </div>
            </div>
        </div>
    )
}

export default RegistrationForm;