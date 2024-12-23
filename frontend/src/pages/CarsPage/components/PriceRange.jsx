import PropTypes from 'prop-types';
import "./PriceRange.css";
import {useState, useEffect} from "react";

const PriceRange = ({ min, max, value, step, onChange }) => {
    const [minValue, setMinValue] = useState(value ? value.min : min);
    const [maxValue, setMaxValue] = useState(value ? value.max : max);

    useEffect(() => {
        if (value) {
            setMinValue(value.min);
            setMaxValue(value.max);
        }
    }, [value]);

    const handleMinChange = (e) => {
        e.preventDefault();
        const newMinVal = Math.max(min, Math.min(+e.target.value, maxValue - step));
        setMinValue(newMinVal);
        onChange({ min: newMinVal, max: maxValue });
    };

    const handleMaxChange = (e) => {
        e.preventDefault();
        const newMaxVal = Math.min(max, Math.max(+e.target.value, minValue + step));
        setMaxValue(newMaxVal);
        onChange({ min: minValue, max: newMaxVal });
    };

    const minPos = Math.max(0, Math.min(((minValue - min) / (max - min)) * 100, 100));
    const maxPos = Math.max(0, Math.min(((maxValue - min) / (max - min)) * 100, 100));

    return (
        <div className='price-range__wrapper'>
            <div className='input-wrapper'>
                <input
                    className='input'
                    type='range'
                    value={minValue}
                    min={min}
                    max={max}
                    step={step}
                    onChange={handleMinChange}
                />
                <input
                    className='input'
                    type='range'
                    value={maxValue}
                    min={min}
                    max={max}
                    step={step}
                    onChange={handleMaxChange}
                />
            </div>

            <div className='control-wrapper'>
                <div className='control' style={{left: `${minPos}%`}}/>
                <div className='rail'>
                    <div
                        className='inner-rail'
                        style={{left: `${minPos}%`, right: `${100 - maxPos}%`}}
                    />
                </div>
                <div className='control' style={{left: `${maxPos}%`}}/>
            </div>
        </div>
    );
};

PriceRange.propTypes = {
    min: PropTypes.number.isRequired,
    max: PropTypes.number.isRequired,
    value: PropTypes.shape({
        min: PropTypes.number.isRequired,
        max: PropTypes.number.isRequired
    }).isRequired,
    step: PropTypes.number.isRequired,
    onChange: PropTypes.func.isRequired
}

export default PriceRange;