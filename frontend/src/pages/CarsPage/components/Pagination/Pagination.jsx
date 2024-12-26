import PropTypes from 'prop-types';
import './Pagination.css';

const Pagination = ({ currentPage, totalPages, onPageChange }) => {
    const pages = [...Array(totalPages).keys()].map((i) => i + 1);

    return (
        <div className="pagination">
            {currentPage > 1 && (
                <button
                    className="pagination-arrow"
                    onClick={() => onPageChange(currentPage - 1)}
                >
                    &larr;
                </button>
            )}

            {pages.map((page) => (
                <button
                    key={page}
                    className={`pagination-number`}
                    onClick={() => onPageChange(page)}
                >
                    {page}
                </button>
            ))}

            {currentPage < totalPages && (
                <button
                    className="pagination-arrow"
                    onClick={() => onPageChange(currentPage + 1)}
                >
                    &rarr;
                </button>
            )}
        </div>
    );
};

Pagination.propTypes = {
    currentPage: PropTypes.number.isRequired,
    totalPages: PropTypes.number.isRequired,
    onPageChange: PropTypes.func.isRequired,
};

export default Pagination;