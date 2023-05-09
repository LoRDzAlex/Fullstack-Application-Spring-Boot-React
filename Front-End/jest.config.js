module.exports = {
    testEnvironment: 'jsdom',
    transformIgnorePatterns: [
        '/node_modules/(?!@mui|react-syntax-highlighter|react-popper)',
        "/node_modules/(?!(axios)/)"
    ],
};